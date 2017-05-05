package com.aaront.exercise.impl;

import com.aaront.exercise.api.Connection;
import com.aaront.exercise.api.ConnectionException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionImpl implements Connection {
    private BufferedInputStream bis;
    private int contentLength;

    public ConnectionImpl(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        bis = new BufferedInputStream(connection.getInputStream());
        contentLength = connection.getContentLength();
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        long skipped = bis.skip(startPos);
        while(skipped < startPos) {
            skipped += bis.skip(startPos - skipped);
        }
        byte[] content = new byte[endPos - startPos + 1];
        int len = bis.read(content, 0, content.length);
        while (len < content.length) {
           len += bis.read(content, len, content.length - len);
           System.out.println(len);
        }
        return content;
    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

    @Override
    public void close() {
        try {
            bis.close();
        } catch (IOException e) {
            throw new ConnectionException("连接关闭失败");
        }
    }
}