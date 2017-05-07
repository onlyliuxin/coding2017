package com.coderising.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection connection = new ConnectionImpl();
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)u.openConnection();
			connection.setConnection(conn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
