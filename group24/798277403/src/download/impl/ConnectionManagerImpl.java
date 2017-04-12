package download.impl;

import download.api.Connection;
import download.api.ConnectionException;
import download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		return new ConnectionImpl(url);
	}

}
