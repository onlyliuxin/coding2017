package com.coderising.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	private HttpURLConnection httpConnection;
	
	@Override
	public Connection open(String url,int startPos,int endPos) throws ConnectionException {
		
		if (url != null && !("".equals(url))) {
			try {
				URL urlconn = new URL(url);
				httpConnection = (HttpURLConnection) urlconn.openConnection();
				httpConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
				return new ConnectionImpl(httpConnection);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int getContentLength(String url) {
		// TODO Auto-generated method stub
		if (url != null && !("".equals(url))) {
			try {
				URL urlconn = new URL(url);
				httpConnection = (HttpURLConnection) urlconn.openConnection();
				httpConnection.disconnect();
				return httpConnection.getContentLength();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

}
