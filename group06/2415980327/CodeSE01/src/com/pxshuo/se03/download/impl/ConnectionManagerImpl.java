package com.pxshuo.se03.download.impl;

import com.pxshuo.se03.download.api.Connection;
import com.pxshuo.se03.download.api.ConnectionException;
import com.pxshuo.se03.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
	
	@Override
	public Connection open(String url) throws ConnectionException {
		return new ConnectionImpl(url);
	}

}
