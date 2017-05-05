package com.java.xiaoqin.download.impl;

import com.java.xiaoqin.download.api.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;


public class ConnectionImpl implements Connection {

	HttpURLConnection conn;

	public ConnectionImpl(HttpURLConnection conn) {
		this.conn = conn;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) conn.getURL().openConnection();
		connection.setRequestProperty("Range","bytes=" + startPos + "-" + endPos);
		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_PARTIAL){
			byte[] buff = new byte[endPos - startPos + 1];
			InputStream is = connection.getInputStream();
			int read = is.read(buff);
			is.close();
			connection.disconnect();
			return buff;
		}
		return null;
	}

	@Override
	public int getContentLength() {
		int contentLength = 0;
		try {
			if (null != conn && HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                contentLength = conn.getContentLength();
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentLength;
	}

	@Override
	public void close() {
		if (null != conn){
			conn.disconnect();
		}
	}

}
