package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		try {
			URL fileurl=new URL(url);
			HttpURLConnection uRlconn = (HttpURLConnection)fileurl.openConnection();
			  //设置连接的相关属性  
			uRlconn.setRequestMethod("GET");  
			uRlconn.setReadTimeout(5000);  
			Connection conn = new ConnectionImpl();
			conn.setFileurl(fileurl);
			
			conn.setConn(uRlconn);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
