package com.github.xiaozi123.coding2017.thirdWork.impl;

import java.net.MalformedURLException;
import java.net.URL;

import com.github.xiaozi123.coding2017.thirdWork.api.Connection;
import com.github.xiaozi123.coding2017.thirdWork.api.ConnectionException;
import com.github.xiaozi123.coding2017.thirdWork.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		return new ConnectionImpl(url);
	}

}
