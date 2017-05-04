package com.sprint.download.impl;

import com.sprint.download.api.Connection;
import com.sprint.download.api.ConnectionException;
import com.sprint.download.api.ConnectionManager;
public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		return new ConnectionImpl(url);
	}
}


