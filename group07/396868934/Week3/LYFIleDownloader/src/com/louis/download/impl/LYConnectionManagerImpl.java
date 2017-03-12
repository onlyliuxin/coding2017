package com.louis.download.impl;

import java.net.MalformedURLException;
import java.net.URL;

import com.louis.download.api.LYConnection;
import com.louis.download.api.LYConnectionException;
import com.louis.download.api.LYConnectionManager;

public class LYConnectionManagerImpl implements LYConnectionManager {

	@Override
	public LYConnection open(String urlString) throws LYConnectionException {
		LYConnectionImpl connection = null;
		try {
			URL url = new URL(urlString);
			connection = new LYConnectionImpl(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new LYConnectionException("URL格式错误:" + e.getMessage());
		}
		return connection;
	}
}
