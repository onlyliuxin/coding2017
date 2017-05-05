package com.coding.basic.homework_03.download.impl;

import java.io.IOException;

import com.coding.basic.homework_03.download.api.Connection;
import com.coding.basic.homework_03.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws IOException {
		Connection conn = new ConnectionImpl(url);
		return conn;
	}

}
