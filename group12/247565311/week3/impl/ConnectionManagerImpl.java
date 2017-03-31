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
		try {
			URL urllink = new URL(url);
			conImpl = new ConnectionImpl();
			HttpURLConnection httpconn = (HttpURLConnection)urllink.openConnection();
			httpconn.setConnectTimeout(5*1000);
			httpconn.setRequestProperty("User-Agent","Mozilla/4.0 (compatiable; MSIE 5.0; Windows NT; DigExt)"); // Ä£Äâä¯ÀÀÆ÷
			conImpl.setConn(httpconn);
		} catch (Exception e) {
			throw (ConnectionException)e;
		}
		 return conImpl;
	}
}
