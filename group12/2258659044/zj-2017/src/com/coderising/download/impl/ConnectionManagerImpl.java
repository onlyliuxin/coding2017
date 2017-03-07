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
	public Connection open(String url) throws ConnectionException {
		
		URL remotUrl = null;
		HttpURLConnection urlCon = null;
		ConnectionImpl conn = new ConnectionImpl();
		try {
			remotUrl = new URL(url);    
			urlCon = (HttpURLConnection)remotUrl.openConnection();
			conn.setUrlCon(urlCon);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return conn;
	}
}