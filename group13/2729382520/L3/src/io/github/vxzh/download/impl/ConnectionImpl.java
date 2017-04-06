package io.github.vxzh.download.impl;

import io.github.vxzh.download.api.Connection;
import io.github.vxzh.download.api.ConnectionException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionImpl implements Connection {


    private String path;

    private static final int BUFFER_MAX_SIZE = 1024;

    public ConnectionImpl(String path) {
        this.path = path;
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException, ConnectionException {

        if (startPos > endPos)
            throw new ConnectionException("startPos > endPos ");

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_PARTIAL) {
            int blockSize = endPos - startPos + 1;
            InputStream is = conn.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[Math.min(blockSize, BUFFER_MAX_SIZE)];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            is.close();
            return outputStream.toByteArray();


        } else {
            throw new ConnectionException("response code: " + responseCode);
        }


    }

    @Override
    public int getContentLength() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn.getContentLength();
    }

    @Override
    public void close() {

    }

}
