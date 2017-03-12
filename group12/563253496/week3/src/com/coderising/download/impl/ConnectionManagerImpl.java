package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {


	private  String url;
	@Override
	public Connection open(String url) throws ConnectionException {
		this.url=url;

		Connection conn = new ConnectionImpl(url);


		return conn;
	}

}
