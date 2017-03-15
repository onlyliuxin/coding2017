package com.coderising.download.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		URL urlObj;
		ConnectionImpl connection = null;
		URLConnection conn = null;
		
		try {
			//打开一个连接
			urlObj = new URL(url);
			conn = urlObj.openConnection();
			
			connection = new ConnectionImpl();
			connection.setConnection(conn);
			
		} catch (MalformedURLException e) {
			
		}catch (IOException e) {
			
		}

		return connection;
	}

}
