package com.multiThreadDownload.impl;

import com.multiThreadDownload.api.Connection;
import com.multiThreadDownload.api.ConnectionException;
import com.multiThreadDownload.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
