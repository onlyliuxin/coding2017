package com.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private URL urlObj;
	private static final int MAX_BUFFER_SIZE = 1024;

	public ConnectionImpl(URL urlObj) {
		this.urlObj = urlObj;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		URLConnection connection = urlObj.openConnection();  
		connection.setAllowUserInteraction(true);  
		connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream inputStream = connection.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[MAX_BUFFER_SIZE];
		int length = 0;
		while(-1 != (length = inputStream.read(buffer))){
			baos.write(buffer, 0, length);
		}
		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		int length = 0;
		try {
			HttpURLConnection hUrlConn = (HttpURLConnection)urlObj.openConnection();
			length = hUrlConn.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return length;
	}

	@Override
	public void close() {
		
	}

}
