package week3_0312.impl;

import week3_0312.api.Connection;
import week3_0312.api.ConnectionException;
import week3_0312.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		return new ConnectionImpl(url);
	}

}
