package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws Exception {
		ConnectionImpl con = new ConnectionImpl();
		URL ur = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
		// 设置通用的请求属性
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		// 建立实际的连接
		conn.connect();
		con.setConn(conn);
		return con;
	}

}
