package download.impl;

import download.api.Connection;
import download.api.ConnectionException;
import download.api.ConnectionManager;

import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		return null;
	}

	public int getContentLength(String url) throws ConnectionException{
		try {
			URL newURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
