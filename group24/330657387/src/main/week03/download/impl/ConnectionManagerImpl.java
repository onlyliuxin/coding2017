package main.week03.download.impl;

import main.week03.download.api.Connection;
import main.week03.download.api.ConnectionException;
import main.week03.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection res = new ConnectionImpl(url);
		
		return res;
	}

}
