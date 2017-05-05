package com.github.fei9009.coderising0305.download.impl;

import com.github.fei9009.coderising0305.download.api.Connection;
import com.github.fei9009.coderising0305.download.api.ConnectionException;
import com.github.fei9009.coderising0305.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		return new ConnectionImpl(url);
	}

}
