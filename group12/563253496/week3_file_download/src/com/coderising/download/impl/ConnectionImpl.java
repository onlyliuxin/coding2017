package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {

    static final int BUFFER_SIZE = 1024;
    URL url;

    ConnectionImpl(String _url) {
        try {
            url = new URL(_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        InputStream is = httpConn.getInputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int totalLen = endPos - startPos + 1;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        while (baos.size() < totalLen) {
            int len = is.read(buffer);
            if (len < 0) {
                break;
            }
            baos.write(buffer);
        }
        if (baos.size() > totalLen) {
            byte[] temp = baos.toByteArray();
            return Arrays.copyOf(temp, totalLen);
        }
        return baos.toByteArray();


    }

    @Override
    public int getContentLength() {
        try {
            URLConnection conn = url.openConnection();
            return conn.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;


    }

    @Override
    public void close() {


    }

}
