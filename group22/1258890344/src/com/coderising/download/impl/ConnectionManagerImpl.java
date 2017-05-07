package com.coderising.download.impl;


import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
	URL url;
	@Override
	public Connection open(String url) throws ConnectionException {
		try {
			this.url=new URL(url);
			return new ConnectionImpl(this.url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
