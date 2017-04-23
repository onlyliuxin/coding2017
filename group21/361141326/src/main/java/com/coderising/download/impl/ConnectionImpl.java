package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionImpl implements Connection {

    private URL url;

    private int contentLength;

    public ConnectionImpl(String url) throws ConnectionException {
        try {
            URL path = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) path.openConnection();
            contentLength = urlConnection.getContentLength();
            System.out.println(urlConnection.getResponseMessage());
            urlConnection.getInputStream().close();

            this.url = path;
        } catch (IOException e) {
            throw new ConnectionException("connect error", e);
        }
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

        byte[] result = new byte[endPos - startPos + 1];

        InputStream inputStream = null;
        byte[] buff = new byte[1024];
        int length;
        int index = 0;
        try {
            inputStream = connection.getInputStream();
            while ((length = inputStream.read(buff)) > 0) {
                System.arraycopy(buff, 0, result, index, length);
                index += length;
            }
        } finally {
            if (inputStream != null) inputStream.close();
        }

        return result;
    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

    @Override
    public void close() {


    }

}
