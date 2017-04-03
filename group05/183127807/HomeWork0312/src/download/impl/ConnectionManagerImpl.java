package download.impl;

import download.api.Connection;
import download.api.ConnectionException;
import download.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String fileURL,int startPos,int endPos) throws ConnectionException {
		Connection coon = null;
		try {
			URL url = new URL(fileURL);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

			httpURLConnection.setConnectTimeout(5000);
			coon = new ConnectionImpl(httpURLConnection);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return coon;
	}

	public int getContentLength(String url) throws ConnectionException{

		int contentLength=0;
		try {
			URL fileURL = new URL(url);
			HttpURLConnection httpURLConnection=(HttpURLConnection)fileURL.openConnection();
			int response = httpURLConnection.getResponseCode();

			if (response == 206) {
				contentLength = httpURLConnection.getContentLength();
				httpURLConnection.disconnect();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentLength;
	}

}
