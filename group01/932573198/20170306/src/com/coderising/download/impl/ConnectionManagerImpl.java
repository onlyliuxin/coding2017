package com.coderising.download.impl;

import java.io.IOException;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	private Connection conn = null;
	
	@Override
	public Connection open(String url) {
		try {
			conn = new ConnectionImpl(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
