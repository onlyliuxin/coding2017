package com.coding.download.impl;

import com.coding.download.api.Connection;
import com.coding.download.api.ConnectionException;
import com.coding.download.api.ConnectionManager;

import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		URL urlObj;
		try {
			urlObj = new URL(url);
		} catch (MalformedURLException e) {
			throw new ConnectionException("URL无法访问" + e.getMessage());
		}
		return  new ConnectionImpl(urlObj);
	}

}
