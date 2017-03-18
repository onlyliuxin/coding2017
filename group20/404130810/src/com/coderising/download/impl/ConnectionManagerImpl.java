package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
	@Override
	public Connection open(String urlStr) throws ConnectionException {
		Connection conn = new ConnectionImpl(urlStr);
		return conn;
	}
}