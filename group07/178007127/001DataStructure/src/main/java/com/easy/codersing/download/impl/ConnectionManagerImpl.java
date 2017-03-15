package com.easy.codersing.download.impl;

import java.net.URL;
import com.easy.codersing.download.api.Connection;
import com.easy.codersing.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String path) throws  Exception {
		URL url =new URL(path);
		Connection conn=new ConnectionImpl(url);
		return conn;
	}

}
