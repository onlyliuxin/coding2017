package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private HttpURLConnection conn;
	
	private InputStream inputStream;
	
	private ByteArrayOutputStream outputStream;
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] bytes = new byte[1024];
		conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		inputStream = conn.getInputStream();
		outputStream = new ByteArrayOutputStream();
		int n = 0;
		while((n = inputStream.read(bytes)) != -1){
			outputStream.write(bytes, 0, n);
		}
		return outputStream.toByteArray();
	}

	@Override
	public int getContentLength() {
		return conn.getContentLength();
	}

	@Override
	public void close() {
		conn.disconnect();
		if(inputStream != null){
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(outputStream != null){
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public HttpURLConnection getConnection() {
		return conn;
	}

	public void setConnection(HttpURLConnection conn) {
		this.conn = conn;
	}

}
