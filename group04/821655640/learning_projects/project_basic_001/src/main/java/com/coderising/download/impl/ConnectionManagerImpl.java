package com.coderising.download.impl;


import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	public Connection open(String url,int startPos ,int endPos) throws ConnectionException {
		if (null == url || "".equals(url)) {
			throw new IllegalArgumentException("参数异常");
		}
		
		Connection conn = null;
		try {
			conn = new ConnectionImpl(url,startPos,endPos);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConnectionException();
		}
		
		return conn;
	}
	
	public Connection open(String url) throws ConnectionException {
		if (null == url || "".equals(url)) {
			throw new IllegalArgumentException("参数异常");
		}
		
		Connection conn = null;
		try {
			conn = new ConnectionImpl(url);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConnectionException();
		}
		
		return conn;
	}

}
