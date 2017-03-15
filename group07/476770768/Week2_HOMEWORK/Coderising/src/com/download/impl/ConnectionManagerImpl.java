package com.download.impl;

import java.net.MalformedURLException;
import java.net.URL;

import com.download.api.Connection;
import com.download.api.ConnectionException;
import com.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
	

	@Override
	public Connection open(String url) throws ConnectionException {
		URL urlObj;
		ConnectionImpl connection = null;
		try {
			urlObj = new URL(url);
			connection = new ConnectionImpl(urlObj);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
