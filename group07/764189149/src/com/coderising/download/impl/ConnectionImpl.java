package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private URL urlObj;

	private static final int BUFFER_MAX_SIZE = 1024;

	public ConnectionImpl(URL urlObj){
		this.urlObj = urlObj;
	}
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		URLConnection connection = urlObj.openConnection();  
		connection.setAllowUserInteraction(true);  
		connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos); 
		InputStream inputstream = connection.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		byte[] buffer = new byte[BUFFER_MAX_SIZE];
		int length = 0;
		while(-1 != (length = inputstream.read(buffer))){
			bos.write(buffer , 0 , length);
		}
		inputstream.close();
		bos.close();
		return bos.toByteArray();
	}

	@Override
	public int getContentLength() {
		int length = 0;

		try {
			HttpURLConnection conn = (HttpURLConnection)urlObj.openConnection();
			length = conn.getContentLength();
		} catch (IOException e) {
			System.out.println("getContentLength error:"+e.getMessage());
		}
		return length;
	}

	@Override
	public void close() {

	}
}
