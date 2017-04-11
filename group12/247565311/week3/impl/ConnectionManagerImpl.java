package week3.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import week3.api.Connection;
import week3.api.ConnectionException;
import week3.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
    URL urllink = null;
    ConnectionImpl conImpl = null;
	@Override
	public Connection open(String url) throws ConnectionException {
		 return new ConnectionImpl(url);
	}
}
