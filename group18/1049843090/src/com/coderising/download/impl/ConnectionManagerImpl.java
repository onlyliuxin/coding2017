package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		try {
			URL httUrl = new URL(url);
			ConnectionImpl connectionImpl = new ConnectionImpl();
			connectionImpl.setHttpURLConnection((HttpURLConnection) httUrl.openConnection());
			return connectionImpl;
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}