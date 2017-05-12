package com.coding.week3.download1.impl;


import com.coding.week3.download1.api.Connection;
import com.coding.week3.download1.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String url) throws IOException {
        HttpURLConnection httpURLConnection = null;
        // 统一资源
        URL realurl = new URL(url);
        // 连接类的父类，抽象类
        URLConnection urlConnection = realurl.openConnection();
        // http的连接类
        httpURLConnection = (HttpURLConnection) urlConnection;
        //设置属性
        setHeader(httpURLConnection);

        //设置连接超时时间
        setWaitTime(httpURLConnection);

        // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
        httpURLConnection.connect();
        return new ConnectionImpl(httpURLConnection);
    }

    private void setHeader(HttpURLConnection con){
        con.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008092510 Ubuntu/8.04 (hardy) Firefox/3.0.3");
        con.setRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");
        con.setRequestProperty("Accept-Encoding", "aa");
        con.setRequestProperty("Accept-Charset",
                "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        con.setRequestProperty("Keep-Alive", "300");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("If-Modified-Since",
                "Fri, 02 Jan 2009 17:00:05 GMT");
        con.setRequestProperty("If-None-Match", "\"1261d8-4290-df64d224\"");
        con.setRequestProperty("Cache-Control", "max-age=0");
        con.setRequestProperty("Referer",
                "http://www.skycn.com/soft/14857.html");
    }


    private void setWaitTime(HttpURLConnection con) {
        //防止网络阻塞，设置指定的超时时间；单位都是ms。超过指定时间，就会抛出异常
        con.setConnectTimeout(10000); //连接超时设置
        con.setReadTimeout(10000); //读取超时设置
    }

}
