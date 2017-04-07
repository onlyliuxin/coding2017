package com.xiaol.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.xiaol.download.api.Connection;

public class ConnectionImpl implements Connection {

	private HttpURLConnection httpURLConnection;

	public ConnectionImpl(String url) {
		try {
			this.httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.setConnectTimeout(5000);
		httpURLConnection.setReadTimeout(5000);
		httpURLConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		httpURLConnection.connect();
		List list = new ArrayList<>();
		byte[] byteArray = null;
		byte[] read = new byte[1024];
		int length = 0;
		if (httpURLConnection.getResponseCode() == 206) {
			InputStream inputStream = httpURLConnection.getInputStream();
			byteArray = new byte[endPos - startPos + 1];
			while ((length = inputStream.read(byteArray)) != -1) {
				
			}
			inputStream.close();
		}
		close();
		return byteArray;
	}

	@Override
	public int getContentLength() {
		return httpURLConnection.getContentLength();
	}

	@Override
	public void close() {
		httpURLConnection.disconnect();
	}

}