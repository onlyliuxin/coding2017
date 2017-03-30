package com.coding.week3.download.impl;


import com.coding.week3.download.api.Connection;
import com.coding.week3.download.api.ConnectionException;
import com.coding.week3.download.api.ConnectionManager;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String url) throws ConnectionException {
        try {
            // 统一资源
            URL realurl = new URL(url);
            // 连接类的父类，抽象类
            URLConnection urlConnection = realurl.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            return new ConnectionImpl(httpURLConnection);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            throw new ConnectionException(e.getMessage());
        }
    }

}
