package org.coding.three.download.impl;

import org.coding.three.download.api.Connection;
import org.coding.three.download.api.ConnectionException;
import org.coding.three.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		return new ConnectionImpl(url);
	}

}
