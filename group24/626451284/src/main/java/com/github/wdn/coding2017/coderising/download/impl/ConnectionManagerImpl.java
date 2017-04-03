package com.github.wdn.coding2017.coderising.download.impl;


import com.github.wdn.coding2017.coderising.download.api.Connection;
import com.github.wdn.coding2017.coderising.download.api.ConnectionException;
import com.github.wdn.coding2017.coderising.download.api.ConnectionManager;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		try {
			URL targetUrl = new URL(url);
			Connection connection = new ConnectionImpl();
			connection.setHttpURLConnection((HttpURLConnection) targetUrl.openConnection());
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
