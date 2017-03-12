package com.aaront.exercise.impl;

import com.aaront.exercise.api.Connection;
import com.aaront.exercise.api.ConnectionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConnectionImpl implements Connection {

    private FileInputStream fis;
    private File file;

    public ConnectionImpl(String source) throws FileNotFoundException {
        file = new File(getClass().getClassLoader().getResource(source).getFile());
        fis = new FileInputStream(file);
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        fis.skip(startPos);
        byte[] content = new byte[endPos - startPos + 1];
        fis.read(content, 0, content.length);
        return content;
    }

    @Override
    public int getContentLength() {
        return (int)file.length();
    }

    @Override
    public void close() {
        try {
            fis.close();
        } catch (IOException e) {
            throw new ConnectionException("连接关闭失败");
        }
    }
}