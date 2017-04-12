package impl;

import api.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class ConnectionImpl implements Connection {
	private HttpURLConnection urlConnection;
	private InputStream inputStream;
	public ConnectionImpl(HttpURLConnection urlConnection) {
		this.urlConnection=urlConnection;

    }

    @Override
	public byte[] read(int startPos, int endPos) throws IOException {//TODO 实际下载
		urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		urlConnection.connect();
		InputStream inputStream = urlConnection.getInputStream();
		byte[] buffer = new byte[endPos-startPos+1];
		inputStream.read(buffer);
		return buffer;
	}

	@Override
	public int getContentLength() {
		try {
			urlConnection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return urlConnection.getContentLength();

	}

	@Override
	public void close() {
		
		
	}

}
