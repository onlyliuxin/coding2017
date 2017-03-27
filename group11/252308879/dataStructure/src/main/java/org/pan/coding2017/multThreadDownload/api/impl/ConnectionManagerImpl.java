package org.pan.coding2017.multThreadDownload.api.impl;


import org.pan.coding2017.multThreadDownload.api.Connection;
import org.pan.coding2017.multThreadDownload.api.ConnectionManager;

import java.io.IOException;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) {
		try {
			ConnectionImpl conn=null;
			conn=new ConnectionImpl(url);
			return conn;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
