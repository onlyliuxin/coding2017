package com.coderising.download.impl;

import java.io.FileNotFoundException;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		ConnectionImpl conn = null;
		try {
			conn = new ConnectionImpl(url);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
