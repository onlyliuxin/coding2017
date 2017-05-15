package com.dudy.learn01.coderising.download.impl;



import com.dudy.learn01.coderising.download.api.Connection;
import com.dudy.learn01.coderising.download.api.ConnectionException;
import com.dudy.learn01.coderising.download.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {


	private Connection connection = null;

	@Override
    public Connection open(String url) throws ConnectionException, IOException {

		connection = new ConnectionImpl(url);

		return connection;
	}

}