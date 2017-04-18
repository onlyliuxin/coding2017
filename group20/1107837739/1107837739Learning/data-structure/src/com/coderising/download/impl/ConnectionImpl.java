package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class ConnectionImpl implements Connection {

    private static final int BUFFER_SIZE = 4096;
    private HttpURLConnection httpConn;
    private String fileUrl;
    private InputStream inputStream;

    public ConnectionImpl(HttpURLConnection httpConn, String fileUrl) {
        this.httpConn = httpConn;
        this.fileUrl = fileUrl;
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        if (endPos < startPos) {
            throw new IllegalArgumentException("argument endPos[" + endPos + "] less than startPos[" + startPos + "]");
        }
        int bytesNeed2Read = endPos - startPos + 1;
        if (bytesNeed2Read > getContentLength()) {
            throw new IllegalArgumentException(
                    "endPos[" + endPos + "] is bigger than content length[" + getContentLength() + "]");
        }

        inputStream = httpConn.getInputStream();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[Math.min(bytesNeed2Read, BUFFER_SIZE)];
        int read;

        long startTime = System.currentTimeMillis();
        final long progressInterval = 2000;
        while ((read = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, read);

            if (System.currentTimeMillis() - startTime > progressInterval) {
                startTime = System.currentTimeMillis();
                System.out.println(String.format(Thread.currentThread().getName() +
                        " [%.2f%%]", byteArrayOutputStream.size() * 100.0 / bytesNeed2Read)
                );
            }
        }
        System.out.println(String.format(Thread.currentThread().getName() + " [%.2f%%]", 100.0));
        System.out.println("bytes read: " + byteArrayOutputStream.size());

        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public int getContentLength() {
        if (httpConn != null) {
            return httpConn.getContentLength();
        }
        return 0;
    }

    @Override
    public void close() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (httpConn != null) {
            httpConn.disconnect();
        }
    }

    @Override
    public String getFileName() {
        String disposition = httpConn.getHeaderField("Content-Disposition");
        if (disposition != null) {
            // extracts file name from header field
            int index = disposition.indexOf("filename=");
            if (index > 0) {
                return disposition.substring(index + 10,
                        disposition.length() - 1);
            }
        }
        // extracts file name from URL
        return fileUrl.substring(fileUrl.lastIndexOf("/") + 1,
                fileUrl.length());
    }
}