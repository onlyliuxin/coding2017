package com.bruce.homework0312.download.impl;

import com.bruce.homework0312.download.api.Connection;
import com.bruce.homework0312.download.api.ConnectionException;
import com.bruce.homework0312.download.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String path) throws ConnectionException, IOException {
		return new ConnectionImpl(path);
	}

}
