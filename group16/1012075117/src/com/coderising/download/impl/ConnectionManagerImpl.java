package com.coderising.download.impl;

import java.io.IOException;
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
		URL netURL = null;
		URLConnection urlConnection = null;
		HttpURLConnection httpURLConnection = null;
		ConnectionImpl connectionImpl = null;
		
		try {
			netURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			urlConnection = netURL.openConnection();
			httpURLConnection = (HttpURLConnection) urlConnection;
			httpURLConnection.connect();
			connectionImpl = new ConnectionImpl(httpURLConnection, url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connectionImpl;
	}

}