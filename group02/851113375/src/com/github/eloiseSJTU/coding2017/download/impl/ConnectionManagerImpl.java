package com.github.eloiseSJTU.coding2017.download.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.github.eloiseSJTU.coding2017.download.api.Connection;
import com.github.eloiseSJTU.coding2017.download.api.ConnectionException;
import com.github.eloiseSJTU.coding2017.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection connection = null;
		try {
			connection = new ConnectionImpl(new URL(url).openConnection());
		} catch (MalformedURLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		return connection;
	}

}
