package com.coding.download.impl;

import com.coding.download.api.Connection;
import com.coding.download.api.ConnectionException;
import com.coding.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String urlLocation) throws ConnectionException {
		Connection conn = new ConnectionImpl(urlLocation);
		return conn;
	}

}
