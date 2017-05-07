package com.coderising.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String paramurl) throws ConnectionException {
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL(paramurl);
			conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");  
	        conn.setReadTimeout(5 * 1000); 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Connection cn = new ConnectionImpl(conn);
		return cn;
	}

}