package com.coderising.download.impl;

import xdx.homework.third.download.api.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionImpl implements Connection {

    private HttpURLConnection conn;

    private URL url;

    public ConnectionImpl(String strUrl) throws IOException {
        url = new URL(strUrl);
        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {

        conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        System.out.println("线程_" + Thread.currentThread().getName()
                + "的下载起点是: " + startPos + "  下载终点是: " + endPos);
        byte[] data = new byte[endPos - startPos];
        if (conn.getResponseCode() == 206) {
            InputStream inputStream = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int count = 1;
            while(inputStream.read(buffer) > 0){
                System.arraycopy(buffer, 0, data, count * 1024, 1024);
                count++;
            }
        }
        return data;
    }

    @Override
    public int getContentLength() {
        return conn.getContentLength();
    }

    @Override
    public void close() {
        conn.disconnect();
    }

}
