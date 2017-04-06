package com.coderising.download.impl;


import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

import java.io.IOException;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {

		Connection conn;
		try {
			conn = new ConnectionImpl(url);
		} catch (IOException e) {
			throw new ConnectionException("获取连接失败:" + e.getMessage());
		}
		return conn;
	}

}
