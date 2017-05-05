package com.coderising.download.impl;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	//在connection里实现连接，不再这儿，这里只是返回
	@Override
	public Connection open(String url) throws ConnectionException {
		try {
			
			
			URL uri = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5*1000);
			Connection connImpl = new ConnectionImpl(conn);
			return connImpl;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		return null;
	}

}
