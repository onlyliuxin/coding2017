package com.coderising.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) {
		URL target;
		Connection conn = null;
		try {
			target = new URL(url);
			HttpURLConnection httpUrl;
			httpUrl = (HttpURLConnection) target.openConnection();
			httpUrl.setConnectTimeout(5000);
			httpUrl.setRequestMethod("GET");
			conn = new ConnectionImpl(httpUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
