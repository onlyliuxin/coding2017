package com.coderising.download.impl;

import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection conn = null;
		try {
			URL u = new URL(url);
			HttpURLConnection hConn = (HttpURLConnection) u.openConnection();
			hConn.setConnectTimeout(5000);
			hConn.setReadTimeout(5000);
			hConn.setRequestMethod("GET");
			conn = new ConnectionImpl(hConn);
		} catch (Exception e) {
			throw new ConnectionException("连接打开失败:"+url, e);
		}
		return conn;
	}

}
