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
		try {
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
			ConnectionImpl connection = new ConnectionImpl();
			connection.setHttpURLConnection(conn);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
