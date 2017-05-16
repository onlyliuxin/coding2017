package com.pan.download.impl;




import com.pan.download.api.Connection;
import com.pan.download.api.ConnectionManager;

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
