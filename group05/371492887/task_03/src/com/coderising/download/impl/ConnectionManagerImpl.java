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
		ConnectionImpl con = null;
		try {
			urlObj = new URL(url);
			con = new ConnectionImpl(urlObj);
			con.setConnection(urlObj.openConnection());
		} catch (MalformedURLException e) {
			throw new ConnectionException("URL格式错误: " + e.getMessage());
		} catch (IOException e) {
			throw new ConnectionException("IO异常: " + e.getMessage());
		}
		return con;
	}

}
