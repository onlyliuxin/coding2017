package com.coderising.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	private URL url; 
	@Override
	public Connection open(String path) throws IOException {
		url = new URL(path);
		HttpURLConnection urlCn = (HttpURLConnection) url.openConnection();
		urlCn.setRequestMethod("GET");
		urlCn.setDoOutput(false);
		urlCn.setDoInput(true);
		urlCn.setUseCaches(false);
		ConnectionImpl conn = new ConnectionImpl();
		conn.setConnection(urlCn);
		return conn;
	}

}
