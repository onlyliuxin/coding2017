package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String fileURL, int startPos, int endPos) throws ConnectionException {
        try {
            System.out.println("try to open file url: " + fileURL);

            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            // 设定读取range
            httpConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
            System.out.println("Range: bytes=" + startPos + "-" + endPos);

            int responseCode = httpConn.getResponseCode();

            System.out.println("server replied HTTP code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_PARTIAL) {
                System.out.println("return new ConnectionImpl");
                return new ConnectionImpl(httpConn, fileURL);
            } else {
                throw new ConnectionException("server replied HTTP code: " + responseCode);
            }
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public int getContentLength(String fileURL) throws ConnectionException {
        try {
            System.out.println("try to open file url: " + fileURL);

            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();

            System.out.println("server replied HTTP code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("return contentLength: " + httpConn.getContentLength());
                int contentLength = httpConn.getContentLength();
                httpConn.disconnect();
                return contentLength;
            } else {
                throw new ConnectionException("server replied HTTP code: " + responseCode);
            }
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
    }
}