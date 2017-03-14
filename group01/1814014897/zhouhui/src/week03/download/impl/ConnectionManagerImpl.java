package week03.download.impl;

import week03.download.api.Connection;
import week03.download.api.ConnectionException;
import week03.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		return new ConnectionImpl(url);
	}	
}
