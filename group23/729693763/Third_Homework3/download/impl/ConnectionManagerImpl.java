package com.zhous.download.impl;

import  com.zhous.download.api.*;

import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {



	@Override
	public Connection open(String url) throws ConnectionException {
		Connection ct = null;
		URL u = null;
		try {
			u = new URL(url);
			ct = new ConnectionImpl(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			//URL类不需要关闭
		}
		return ct;
	}

}
