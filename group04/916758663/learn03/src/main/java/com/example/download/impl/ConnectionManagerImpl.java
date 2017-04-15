package com.example.download.impl;


import com.example.download.api.Connection;
import com.example.download.api.ConnectionException;
import com.example.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection conn = new ConnectionImpl(url);
		return conn;
	}

}
