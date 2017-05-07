package xyy.download.impl;

import xyy.download.api.Connection;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by 14258 on 2017/3/14.
 */
public class ConnectionImpl implements Connection {


    private HttpURLConnection httpUrlConnection;//连接
    private String url;//url
    private String contentType;//类型
    private String contentFileName;//文件名
    private int contentLength;//文件长度


    public ConnectionImpl(String url) throws IOException {
        this.url = url;
        httpUrlConnection = createConn(this.url);
        if (httpUrlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            this.contentLength = httpUrlConnection.getContentLength();
            this.contentType = httpUrlConnection.getContentType();
            this.contentFileName = getName();
            System.out.println("contentType" + httpUrlConnection.getContentType() + "fileName" + this.contentFileName + "contentType" + contentType);
        }
    }

    public ConnectionImpl(String url, boolean b) throws IOException {
        close();
        this.url = url;
        httpUrlConnection = createConn(this.url);
    }

    private String getName() {
        String fileName;
        String disposition = httpUrlConnection.getHeaderField("Content-Disposition");
        if (disposition != null && !"".equals(disposition)) {
            fileName = disposition.split(";")[1].split("=")[1].replaceAll("\"", "");
        } else {
            fileName = url.substring(url.lastIndexOf("/") + 1);
        }

        if (fileName != null && !"".equals(fileName)) {
            try {
                fileName = URLDecoder.decode(fileName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            fileName = "file_" + (int) (Math.random() * 10);
        }
        return fileName;
    }


    private HttpURLConnection createConn(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setReadTimeout(10 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "vvv download");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Keep-Alive", "300");
        return conn;
    }


    //读链接
    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        return new byte[0];
    }

    //获取链接长度
    @Override
    public int getContentLength() {
        return this.contentLength;
    }

    //获取文件名字
    @Override
    public String getFileName() {
        return this.contentFileName;
    }

    //关闭连接
    @Override
    public void close() {
        if (httpUrlConnection != null) {
            httpUrlConnection.disconnect();
            httpUrlConnection = null;
        }
    }


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentFileName() {return contentFileName;}

    public void setContentFileName(String contentFileName) {
        this.contentFileName = contentFileName;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
}
