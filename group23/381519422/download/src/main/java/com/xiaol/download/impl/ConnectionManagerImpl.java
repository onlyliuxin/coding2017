package com.xiaol.download.impl;

import com.xiaol.download.api.Connection;
import com.xiaol.download.api.ConnectionException;
import com.xiaol.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection connection = new ConnectionImpl(url);
		return connection;
	}

}