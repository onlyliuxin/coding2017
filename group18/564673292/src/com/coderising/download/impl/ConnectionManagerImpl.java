package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

import java.io.IOException;


public class ConnectionManagerImpl implements ConnectionManager {
	@Override
	public Connection open(String url) throws ConnectionException, IOException {
        return new ConnectionImpl(url);
	}
}
