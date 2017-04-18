package dataStruct.com.coderising.download.impl;

import dataStruct.com.coderising.download.api.Connection;
import dataStruct.com.coderising.download.api.ConnectionException;
import dataStruct.com.coderising.download.api.ConnectionManager;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	public Connection open(String url) throws ConnectionException {

		try {
			URL urlObject = new URL(url);
			HttpURLConnection urlConnection = (HttpURLConnection)urlObject.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setReadTimeout(5000);
			return new ConnectionImpl(urlConnection);
		} catch (java.io.IOException e) {
			throw new ConnectionException("连接失败");
		}
	}

}
