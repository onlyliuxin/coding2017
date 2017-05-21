package download.impl;

import download.api.Connection;
import download.api.ConnectionException;
import download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	/**
	 * 返回的是Connection接口
	 */
	@Override
	public Connection open(String url) throws ConnectionException {
		
		return new ConnectionImpl(url);
	}

}
