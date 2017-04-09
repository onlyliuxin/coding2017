package com.coderising.week03.download.impl;

import com.coderising.week03.download.api.Connection;
import com.coderising.week03.download.api.ConnectionException;
import com.coderising.week03.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		return new ConnectionImpl(url);
	}

}
