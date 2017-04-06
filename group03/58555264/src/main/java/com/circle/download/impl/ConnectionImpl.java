package com.circle.download.impl;

import com.circle.download.api.Connection;
import com.circle.download.api.ConnectionException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by keweiyang on 2017/3/10.
 */
class ConnectionImpl implements Connection {
    private String url;
    private HttpURLConnection conn;
    private File file;

    public ConnectionImpl(String url, File file) throws IOException, ConnectionException {
        this.file = file;
        this.url = url;
        this.conn = init();
    }

    private HttpURLConnection init() throws IOException, ConnectionException {
        URL httpURL = new URL(url);
        this.conn = (HttpURLConnection) httpURL.openConnection();

        this.conn.setRequestMethod("GET");
        this.conn.setRequestProperty("Charset", "UTF-8");
        return conn;
    }

    @Override
    public void read(int startPos, int endPos) throws IOException, ConnectionException {
        URL httpURL = new URL(url);
        this.conn = (HttpURLConnection) httpURL.openConnection();

        this.conn.setRequestMethod("GET");
        this.conn.setRequestProperty("Charset", "UTF-8");
        this.conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

        if (this.getContentLength() < 0) {
            throw new ConnectionException("连接内容小于0");
        }

        int code = conn.getResponseCode();
        RandomAccessFile raf = null;
        try{
            InputStream is = conn.getInputStream();
            raf = new RandomAccessFile(this.file, "rwd");
            raf.seek(startPos);

            byte[] bs = new byte[1024];
            int len = -1;

            while ((len = is.read(bs)) != -1) {
                raf.write(bs, 0, len);
            }
        }finally {
            raf.close();
        }



    }

    @Override
    public int getContentLength() {
        return this.conn.getContentLength();
    }

    @Override
    public void close() {
//        this.conn.

    }
}
