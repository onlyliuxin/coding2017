package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String desiredUrl) throws ConnectionException {
		URL url = null;

		try
		{
			//create the HttpURLConnection
			url = new URL(desiredUrl);
			URLConnection connection = url.openConnection();
			//connection.connect();
			ConnectionImpl connectionimpl = new ConnectionImpl(connection);
			return connectionimpl;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		//return null;
	}

}
