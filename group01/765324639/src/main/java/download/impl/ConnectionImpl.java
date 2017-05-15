package download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import download.api.Connection;

public class ConnectionImpl implements Connection {

    private URL url = null;;

    private HttpURLConnection conn = null;

    public ConnectionImpl(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        InputStream inputStream = conn.getInputStream();
        byte[] result = new byte[endPos - startPos + 1];
        byte[] data = new byte[1024];
        int read = -1;
        int i = 0;
        while ((read = inputStream.read(data, 0, data.length)) != -1) {
            System.arraycopy(data, 0, result, i, read);
            i += read;
        }
        return result;
    }

    @Override
    public int getContentLength() {
        HttpURLConnection openConnection = null;
        try {
            openConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int contentLength = openConnection.getContentLength();
        openConnection.disconnect();
        return contentLength;
    }

    @Override
    public void close() {
    }

}
