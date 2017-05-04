package week3_fileDownloader.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import week3_fileDownloader.api.Connection;
import week3_fileDownloader.api.ConnectionException;
import week3_fileDownloader.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
    URL urllink = null;
    ConnectionImpl conImpl = null;
	@Override
	public Connection open(String url) throws ConnectionException {
		 return new ConnectionImpl(url);
	}
}
