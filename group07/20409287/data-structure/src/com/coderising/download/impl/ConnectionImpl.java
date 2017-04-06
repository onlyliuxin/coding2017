package com.coderising.download.impl;

import com.coderising.download.api.Connection;

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

        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        System.out.println("线程_" + Thread.currentThread().getName()
                + "的下载起点是: " + startPos + "  下载终点是: " + endPos);
        byte[] data = new byte[endPos - startPos];
        if (conn.getResponseCode() == 206) {
            InputStream inputStream = conn.getInputStream();
            int length = inputStream.read(data);
            if (length < 0) {
                System.out.println("该线程下载失败: " + Thread.currentThread().getName());
            } else {
                System.out.println("下载成功: " + Thread.currentThread().getName());
            }
        } else {
            System.out.println("不支持多线程下载: " + url);
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
