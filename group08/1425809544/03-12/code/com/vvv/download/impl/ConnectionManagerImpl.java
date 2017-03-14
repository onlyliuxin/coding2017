package vvv.download.impl;


import vvv.download.api.Connection;
import vvv.download.api.ConnectionException;
import vvv.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
    private String url;
    
	@Override
	public Connection open(String url) throws ConnectionException {
		if(url!=null && (url.startsWith("http://") || url.startsWith("https://"))){
			Connection conn = null;
			if(!url.equals(this.url)){
				conn = new download.impl.ConnectionImpl(url);
			    this.url = url;
			}else{
				conn = new download.impl.ConnectionImpl(url, false);
			}			
		    return conn;
		}
		
		throw new ConnectionException("Connection exception, uri is incorrect.");
	}

	
}
