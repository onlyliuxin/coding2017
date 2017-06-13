package code03.impl;

import code03.api.Connection;
import code03.api.ConnectionException;
import code03.api.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 获取Connection实例
 */

public class ConnectionManagerImpl implements ConnectionManager {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionManagerImpl.class);

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection connection = null;
		try {
			URL _url = new URL(url);
			URLConnection urlConnection = _url.openConnection();
			connection = new ConnectionImpl(urlConnection);
		} catch (MalformedURLException e) {
			logger.error("url {} format error",url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
