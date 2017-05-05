package com.github.FelixCJF.coding2017.coderising.download.impl;


import java.net.URL;

import com.github.FelixCJF.coding2017.coderising.download.api.Connection;
import com.github.FelixCJF.coding2017.coderising.download.api.ConnectionException;
import com.github.FelixCJF.coding2017.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection connection = null;
		try {
			
			connection = new ConnectionImpl(new URL(url).openConnection());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
