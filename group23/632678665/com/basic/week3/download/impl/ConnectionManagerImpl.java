package com.basic.week3.download.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.basic.week3.download.api.Connection;
import com.basic.week3.download.api.ConnectionException;
import com.basic.week3.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
	
	@Override
	public Connection open(String url) throws ConnectionException {
		URL urlPath=null;
		Connection conn=null;
		try {
			urlPath = new URL(url);
			URLConnection urlConnection=urlPath.openConnection();
			//解析文件
			String [] s =urlPath.toString().split("/");
			String fileName=s[s.length-1];
			conn=new ConnectionImpl(urlConnection,fileName);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
