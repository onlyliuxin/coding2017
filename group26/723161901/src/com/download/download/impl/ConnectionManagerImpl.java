package com.impl;

import com.api.Connection;
import com.api.ConnectionException;
import com.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String urlStr) throws ConnectionException {
		
		return new ConnectionImpl(urlStr);
	}

}
