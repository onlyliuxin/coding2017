package org.wsc.coderising.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.wsc.coderising.download.api.Connection;
import org.wsc.coderising.download.api.ConnectionException;
import org.wsc.coderising.download.api.ConnectionManager;

/**
 * 连接池类
 * 
 * @author Administrator
 * @date 2017年3月6日下午7:11:50
 * @version v1.0
 *
 */
public class ConnectionManagerImpl implements ConnectionManager {
	
	@Override
	public Connection open(String _url) throws ConnectionException {
		HttpURLConnection connection = getConnection(_url);
		return new ConnectionImpl(connection);
	}
	
	@Override
	public int getContentLength(String _url) throws ConnectionException {
		HttpURLConnection connection = getConnection(_url);
		int length = 0;
		try {
			checkStatus(connection);
			length = connection.getContentLength();
		} catch (IOException e) {
			new ConnectionException(e);
		}finally {
			connection.disconnect();
		}
		return length;
	}
	
	private HttpURLConnection getConnection(String _url) throws ConnectionException{
			URL url = null;
			HttpURLConnection connection = null;
			try {
				url = new URL(_url);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				return connection;
			} catch (IOException e) {
				new ConnectionException(e);
			}
			return null;
	}
	
	/**
	 * 检查连接状态
	 * @param connection
	 * @throws IOException
	 * @throws ConnectionException
	 */
	private void checkStatus(HttpURLConnection connection) throws IOException, ConnectionException {
		int responseCode = connection.getResponseCode();
		System.out.println("server response code: " + responseCode);
		if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_PARTIAL) {
			throw new ConnectionException("server response code: " + responseCode);
		}
	}

}
