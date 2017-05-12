package coding.coderising.download.impl;

import coding.coderising.download.api.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * @author jiaxun
 */
public class ConnectionImpl implements Connection {

    private HttpURLConnection urlConnection;

    public ConnectionImpl(HttpURLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        InputStream input = urlConnection.getInputStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int length;
        byte[] byteArray = new byte[1024];
        while ((length = input.read(byteArray)) != -1) {
            output.write(byteArray, 0, length);
        }
        return output.toByteArray();
    }

    @Override
    public int getContentLength() {
        return urlConnection.getContentLength();
    }

    @Override
    public void close() {
        urlConnection.disconnect();
    }
}
