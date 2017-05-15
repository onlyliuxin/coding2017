package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	Connection conn = null;
	
	public Connection open(String url) throws ConnectionException {
		
		return conn = new ConnectionImpl(url);
	}

}
