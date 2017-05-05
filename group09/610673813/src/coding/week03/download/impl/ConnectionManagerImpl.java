package coding.week03.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import coding.week03.download.api.Connection;
import coding.week03.download.api.ConnectionException;
import coding.week03.download.api.ConnectionManager;


public class ConnectionManagerImpl implements ConnectionManager {
	Connection conn;

	@Override
	public Connection open(String url) throws ConnectionException {
		try {
			URL urlObj = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlObj.openConnection();
			conn = new ConnectionImpl(httpURLConnection);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
