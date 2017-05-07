package task0312.coderising.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import task0312.coderising.download.api.Connection;
import task0312.coderising.download.api.ConnectionManager;


public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) {
		URL target;
		Connection conn = null;
		try {
			target = new URL(url);
			HttpURLConnection httpUrl = (HttpURLConnection) target.openConnection();
			httpUrl.setConnectTimeout(5000);
			conn = new ConnectionImpl(httpUrl);
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
