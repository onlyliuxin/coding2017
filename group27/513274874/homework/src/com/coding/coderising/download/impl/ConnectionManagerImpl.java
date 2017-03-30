package com.coderising.download.impl;

import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection con;
		try {
			URL newUrl = new URL(url);
			con = new ConnectionImpl(newUrl);
		} catch (MalformedURLException e) {
			throw new ConnectionException("URL格式出现错误"+e.getMessage());
		}
		return con;
	}

}
