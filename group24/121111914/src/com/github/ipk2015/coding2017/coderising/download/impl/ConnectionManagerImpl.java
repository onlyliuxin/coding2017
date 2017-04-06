package com.github.ipk2015.coding2017.coderising.download.impl;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.github.ipk2015.coding2017.coderising.download.api.Connection;
import com.github.ipk2015.coding2017.coderising.download.api.ConnectionException;
import com.github.ipk2015.coding2017.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection conn=new ConnectionImpl(url);
		return conn;
	}
}
