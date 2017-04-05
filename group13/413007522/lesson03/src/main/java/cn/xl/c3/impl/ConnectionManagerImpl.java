package cn.xl.c3.impl;


import cn.xl.c3.api.Connection;
import cn.xl.c3.api.ConnectionException;
import cn.xl.c3.api.ConnectionManager;


public class ConnectionManagerImpl implements ConnectionManager {
	
	@Override
	public Connection open(String url) throws ConnectionException {

		return new ConnectionImpl(url);
	}



}
