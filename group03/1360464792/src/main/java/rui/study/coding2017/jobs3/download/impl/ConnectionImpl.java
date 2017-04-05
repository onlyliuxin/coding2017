package rui.study.coding2017.jobs3.download.impl;


import rui.study.coding2017.jobs3.download.api.Connection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionImpl implements Connection {
    private static int byteSize = 8 * 1024;

    private int contentLength;


    private URL url;

    public ConnectionImpl(URL url) throws IOException {
        this.url = url;
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        contentLength = httpURLConnection.getContentLength();
        httpURLConnection.disconnect();
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        BufferedInputStream bufferedInputStream;

        ByteArrayOutputStream outputStream;

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        outputStream = new ByteArrayOutputStream();
        int temp;
        byte[] bytes = new byte[byteSize];
        while ((temp = bufferedInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, temp);
        }
        bufferedInputStream.close();
        outputStream.flush();
        outputStream.close();
        httpURLConnection.disconnect();
        return outputStream.toByteArray();
    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

    @Override
    public void close() {
    }


}
