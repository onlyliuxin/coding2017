package com.coding.download.impl;


import java.io.IOException;

import com.coding.download.api.Connection;
import com.coding.download.api.ConnectionException;
import com.coding.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
	
	@Override
	public Connection open(String url) throws ConnectionException{
		try {
			return new ConnectionImpl(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
