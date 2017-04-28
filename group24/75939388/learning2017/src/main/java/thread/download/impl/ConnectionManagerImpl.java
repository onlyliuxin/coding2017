package thread.download.impl;

import thread.download.api.Connection;
import thread.download.api.ConnectionException;
import thread.download.api.ConnectionManager;

import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	public Connection open(String url) throws ConnectionException {
		Connection conn = null;
		try{
			conn = new ConnectionImpl(new URL(url));
		}catch(Exception e){
		    e.printStackTrace();
		}
		return conn;
	}
}
