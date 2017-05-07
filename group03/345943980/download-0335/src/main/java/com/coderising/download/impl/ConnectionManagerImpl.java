package com.coderising.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	private Connection conn;

	public Connection open(String url) throws ConnectionException {
		try {
			URL urlObj = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection)urlObj.openConnection();
			conn = new ConnectionImpl(httpConn);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return conn; 
	}

}
