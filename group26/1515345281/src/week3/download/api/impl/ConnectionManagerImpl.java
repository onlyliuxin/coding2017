package week3.download.api.impl;

import java.io.IOException;

import week3.download.api.Connection;
import week3.download.api.ConnectionException;
import week3.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		return new ConnectionImpl(url);
	}

}
