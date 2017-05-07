package com.donaldy.download.impl;

import com.donaldy.download.api.Connection;
import com.donaldy.download.api.ConnectionException;
import com.donaldy.download.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {

		try {
			URL urlName = new URL(url);

			HttpURLConnection connection = (HttpURLConnection) urlName.openConnection();

			connection.setConnectTimeout(8000);

			connection.setRequestMethod("GET");

			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			connection.setRequestProperty("Accept",
				"image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
					+ "application/x-shockwave-flash, application/xaml+xml, "
					+ "application/vnd.ms-xpsdocument, application/x-ms-xbap, "
					+ "application/x-ms-application, application/vnd.ms-excel, "
					+ "application/vnd.ms-powerpoint, application/msword, */*");
			connection.setRequestProperty("Accept-Language", "zh-CN");

			connection.setRequestProperty("Charset", "UTF-8");

			ConnectionImpl conn = new ConnectionImpl();

			conn.setContentLength(connection.getContentLength());

			conn.setInputStream(connection.getInputStream());

			return conn;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
