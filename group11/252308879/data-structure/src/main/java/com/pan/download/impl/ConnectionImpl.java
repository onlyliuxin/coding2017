package com.pan.download.impl;



import com.pan.download.api.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;


public class ConnectionImpl implements Connection {

    private URL url;
    private static final int BUFFER_SIZE = 1024;

    ConnectionImpl(){}

    ConnectionImpl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        InputStream inputStream = urlConnection.getInputStream();

        byte[] buff = new byte[BUFFER_SIZE];
        int totalSize = endPos - startPos + 1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (byteArrayOutputStream.size() < totalSize) {
            int readSize = inputStream.read(buff);
            if (readSize < 0) {
                break;
            }
            byteArrayOutputStream.write(buff, 0, readSize);
        }
        byte[] data = byteArrayOutputStream.toByteArray();
        if (byteArrayOutputStream.size() > totalSize) {
            return Arrays.copyOf(data, totalSize);
        }
        return data;
    }

    @Override
    public int getContentLength() {
        URLConnection con;
        try {
            con = url.openConnection();
            return con.getContentLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void close() {

    }

}
