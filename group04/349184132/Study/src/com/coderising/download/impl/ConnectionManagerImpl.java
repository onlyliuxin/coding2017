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
		
			URL u;
			HttpURLConnection hc ;
			try {
				u = new URL(url);
				hc = (HttpURLConnection) u.openConnection();
				Connection conn = new ConnectionImpl(hc);;
				return conn;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;

		
			
			
			
	}

}
