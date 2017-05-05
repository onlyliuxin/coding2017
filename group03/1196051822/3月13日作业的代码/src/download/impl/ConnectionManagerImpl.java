package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionManagerImpl implements ConnectionManager {


    InputStream is;
    int length ;
    HttpURLConnection connection;
    String name;

	@Override
	public Connection open(String url) throws ConnectionException {

        try {
            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            length = connection.getContentLength();
            name = u.getFile();
            is = u.openStream();
        } catch (MalformedURLException e) {
            System.out.println("URL地址不正确");
        } catch (IOException e) {
            System.out.println("URL打开流失败");
        }
        return new ConnectionImpl(is,length);
	}

}
