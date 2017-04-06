package task3.download.impl;

import task3.download.api.Connection;
import task3.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws Exception {
		return new ConnectionImpl(url);
	}

}
