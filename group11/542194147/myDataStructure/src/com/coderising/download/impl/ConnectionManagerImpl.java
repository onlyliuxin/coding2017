package com.coderising.download.impl;

import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		URL conURL = null;
		try {
			conURL=new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new ConnectionImpl(conURL);
	}

}
