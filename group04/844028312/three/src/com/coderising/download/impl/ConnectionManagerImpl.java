package com.coderising.download.impl;



import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		HttpURLConnection urlConnection;
		ConnectionImpl conImpl=new ConnectionImpl();
			try {
				URL u=new URL(url);
				urlConnection=(HttpURLConnection) u.openConnection();
				conImpl.setUrlConnection(urlConnection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		return conImpl;
	}

}
