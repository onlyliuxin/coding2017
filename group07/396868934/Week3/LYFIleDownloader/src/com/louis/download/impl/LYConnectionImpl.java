package com.louis.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.louis.download.api.LYConnection;

public class LYConnectionImpl implements LYConnection {
	
	private URL url;
	private static final int BUFFER_MAX_SIZE = 1024;
	
	public LYConnectionImpl(URL url) {
		this.url = url;
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		URLConnection connection = url.openConnection();  
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
		try {
			HttpURLConnection connection = (HttpURLConnection)this.url.openConnection();
			return connection.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
