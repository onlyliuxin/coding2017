package code03.v2.impl;


import code03.v2.api.Connection;
import code03.v2.api.ConnectionException;
import code03.v2.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		return new ConnectionImpl(url);
	}

}
