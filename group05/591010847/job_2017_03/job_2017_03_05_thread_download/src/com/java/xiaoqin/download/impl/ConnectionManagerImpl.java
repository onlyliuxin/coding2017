package com.java.xiaoqin.download.impl;


import com.java.xiaoqin.download.api.Connection;
import com.java.xiaoqin.download.api.ConnectionException;
import com.java.xiaoqin.download.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		if(Objects.isNull(url) || "".equals(url)){
			throw new NullPointerException("uri is null");
		}
		Connection connection = null;
		try {
			URL uriU = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) uriU.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			conn.setReadTimeout(5 * 1000);
			connection = new ConnectionImpl(conn);
		} catch (MalformedURLException e){
			e.printStackTrace();
			throw new ConnectionException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ConnectionException();
		}
		return connection;
	}

}
