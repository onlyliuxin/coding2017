package com.github.orajavac.coding2017.download.impl;

import com.github.orajavac.coding2017.download.api.Connection;
import com.github.orajavac.coding2017.download.api.ConnectionException;
import com.github.orajavac.coding2017.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		return new ConnectionImpl(url);
	}

}
