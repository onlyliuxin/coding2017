package com.coding.download.impl;

import com.coding.download.api.Connection;
import com.coding.download.api.ConnectionException;
import com.coding.download.api.ConnectionManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		try {
			URL netUrl = new URL(url);
			return new ConnectionImpl(netUrl.openConnection());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
