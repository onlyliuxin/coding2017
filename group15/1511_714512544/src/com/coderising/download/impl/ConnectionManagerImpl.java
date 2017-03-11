package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 */
	@Override
	public Connection open(String url) throws ConnectionException {
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if(code == 200){
				return new ConnectionImpl(conn);
			}else {
				throw new RuntimeException("打开连接失败");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("url非法");
		} catch (IOException e) {
			throw new RuntimeException("IO异常");
		}
	}

}
