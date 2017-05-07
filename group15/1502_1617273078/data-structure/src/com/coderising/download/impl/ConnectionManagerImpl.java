package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException, IOException {
		URL url1 = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setRequestMethod("GET");
        //conn.setRequestProperty();
		conn.setConnectTimeout(10*1000);
		//防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        ConnectionImpl connection = new ConnectionImpl();
		connection.setHttpURLConnection(conn);
		//conn.connect();
        //conn.getContentLength();
        return connection;
	}

}
