package com.ace.download.impl;


import com.ace.download.api.Connection;
import com.ace.download.api.ConnectionException;
import com.ace.download.api.ConnectionManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {
	private URL urlObj;
	private Connection connection;

	@Override
	public Connection open(String url) {
		try {
			urlObj = new URL(url);
			connection = new ConnectionImpl(urlObj);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return connection;
	}

}
