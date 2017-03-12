package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {

    private static int bufferSize = 1024;
    private URL url;
    private HttpURLConnection huc;
    boolean finished = false;

    public ConnectionImpl(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        InputStream input;
        ByteArrayOutputStream output = new ByteArrayOutputStream(endPos - startPos + 1);

        byte[] buffer = new byte[bufferSize];

        input = huc.getInputStream();
        input.skip(startPos);
        int len = input.read(buffer);
        while (len != -1) {
            output.write(buffer, 0, len);
        }
        this.finished=true;
        return output.toByteArray();
    }

    @Override
    public int getContentLength() {

        return huc.getContentLength();
    }

    @Override
    public void close() {
        huc.disconnect();

    }

    @Override
    public String getName() {
        String fileName = huc.getURL().getFile();
        return fileName.substring(fileName.lastIndexOf("\\") + 1);
    }
    public boolean isFinished(){
        return this.finished;
    }
}
