package com.m0312.download.impl;

import java.io.File;
import java.net.URL;

import com.m0312.download.api.Connection;
import com.m0312.download.api.ConnectionException;
import com.m0312.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	
	@Override
	public Connection open(String url) throws ConnectionException {
		Connection con=new ConnectionImpl(url);
		
		try {
			URL website = new URL(url);
			con.setUrlCon(website.openConnection());//urlcon是真正可以用的con连接
			
		} catch (Exception e) {
		}
		
		return con;
	}
	

}
