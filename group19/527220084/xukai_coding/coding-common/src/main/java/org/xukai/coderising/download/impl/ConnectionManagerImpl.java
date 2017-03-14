package org.xukai.coderising.download.impl;


import org.xukai.coderising.download.api.Connection;
import org.xukai.coderising.download.api.ConnectionException;
import org.xukai.coderising.download.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String urlStr) throws ConnectionException {
		try {
			URL url = new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(false);
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			ConnectionImpl connection = new ConnectionImpl();
			connection.setUrlConnection(urlConnection);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
