package com.vvv.download.impl;

import com.vvv.download.api.Connection;
import com.vvv.download.api.ConnectionException;
import com.vvv.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
    private String url;
    
	@Override
	public Connection open(String url) throws ConnectionException {
		if(url!=null && (url.startsWith("http://") || url.startsWith("https://"))){
			Connection conn = null;
			if(!url.equals(this.url)){
				conn = new ConnectionImpl(url);
			    this.url = url;
			}else{
				conn = new ConnectionImpl(url, false);
			}			
		    return conn;
		}
		
		throw new ConnectionException("Connection exception, uri is incorrect.");
	}

	
}
