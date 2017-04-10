package com.coderising.download.impl;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		ConnectionImpl connection = null;
		URL u = null;
		URLConnection conn = null;
		try {
			u = new URL(url);
			conn = u.openConnection();
			connection = new ConnectionImpl(conn);
			return connection;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}