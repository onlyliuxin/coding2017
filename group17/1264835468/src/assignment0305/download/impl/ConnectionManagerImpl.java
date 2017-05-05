package assignment0305.download.impl;

import java.io.IOException;
import java.net.URL;

import assignment0305.download.api.Connection;
import assignment0305.download.api.ConnectionException;
import assignment0305.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection connection;
		try {
			URL realUrl = new URL(url);
			connection = new ConnectionImpl(realUrl);
		} catch (IOException e) {
			throw new ConnectionException();
		}
		return connection;
	}

}
