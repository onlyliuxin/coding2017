package com.github.mrwengq.tid.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import com.github.mrwengq.tid.api.Connection;
import com.github.mrwengq.tid.api.ConnectionException;
import com.github.mrwengq.tid.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		return new ConnectionImpl(url);
	}

}
