package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
	
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 */
	@Override
	public Connection open(String url) throws ConnectionException {
		return new ConnectionImpl(url);
	}

}
