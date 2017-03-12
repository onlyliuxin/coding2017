package com.coding2017.week3.download.impl;

import com.coding2017.week3.download.api.Connection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public class ConnectionImpl implements Connection {

    private HttpURLConnection urlConnection;

    public ConnectionImpl(HttpURLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        byte[] content = new byte[endPos + 1 - startPos];
        urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + (endPos + 1));
        urlConnection.getInputStream().read(content);
        return content;
    }

    @Override
    public int getContentLength() {
        return urlConnection.getContentLength();
    }

    @Override
    public void close() {
        urlConnection.disconnect();
        urlConnection = null;
    }

}
