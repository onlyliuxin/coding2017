package com.coderising.download.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		URL urlObj;
		ConnectionImpl connection = null;
		try {
			urlObj = new URL(url);
			connection = new ConnectionImpl();
			connection.setConnection(urlObj.openConnection());
		} catch (MalformedURLException e) {
			throw new ConnectionException("URL格式错误");
		}catch (IOException e) {
			throw new ConnectionException("IO异常");
		}

		return connection;
	}

}
