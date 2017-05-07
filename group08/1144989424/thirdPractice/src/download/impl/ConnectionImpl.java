package download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import download.api.Connection;

public class ConnectionImpl implements Connection{
    
    URLConnection urlConn;

	public URLConnection getUrlConn() {
        return urlConn;
    }

    public void setUrlConn(URLConnection urlConn) {
        this.urlConn = urlConn;
    }

    @Override
	public byte[] read(int startPos, int endPos) throws IOException {
		InputStream input = urlConn.getInputStream();
		byte[] bytes = new byte[endPos-startPos];
		input.read(bytes, startPos, endPos);
		return bytes;
	}

	@Override
	public int getContentLength() {
		return urlConn.getContentLength();
	}

	@Override
	public void close() {
		urlConn = null;
	}

}
