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
	public Connection open(String urlStr) throws ConnectionException {
		URL url;
		try {
			url = new URL(urlStr);
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
		} catch (IOException e) {
			e.printStackTrace();
		}   

		return null;
	}

}