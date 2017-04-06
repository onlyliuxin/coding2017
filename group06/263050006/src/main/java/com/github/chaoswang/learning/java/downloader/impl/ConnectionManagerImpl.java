package com.github.chaoswang.learning.java.downloader.impl;

import com.github.chaoswang.learning.java.downloader.api.Connection;
import com.github.chaoswang.learning.java.downloader.api.ConnectionException;
import com.github.chaoswang.learning.java.downloader.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection conn = null;
		if (url.startsWith("http")){
			conn = new ConnectionImpl(url);
		}else if(url.startsWith("ftp")){
			//TODO
		}
		if(conn == null){
			throw new ConnectionException("Failed to get conneciton.");
		}
		return conn;
	}

}