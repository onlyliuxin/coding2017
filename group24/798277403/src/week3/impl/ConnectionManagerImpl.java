package week3.impl;

import week3.api.Connection;
import week3.api.ConnectionException;
import week3.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		return new ConnectionImpl(url);
	}

}
