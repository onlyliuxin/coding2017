package impl;

import api.Connection;
import api.ConnectionException;
import api.ConnectionManager;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {
	private URL url;
	@Override
	public Connection open(String urlStr) throws ConnectionException {
		Connection connection;
		try {
			url= new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			 connection=new ConnectionImpl(urlConnection);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConnectionException();
		}

		return connection;
	}

}
