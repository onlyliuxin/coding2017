package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

import java.io.IOException;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		URL urlObject;
		try {
			urlObject = new URL(url);
			return new ConnectionImpl(urlObject);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ConnectionException();
		}
	}
}
