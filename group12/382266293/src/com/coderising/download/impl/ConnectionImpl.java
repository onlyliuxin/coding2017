package com.coderising.download.impl;

import static util.Print.println;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;

import sun.net.www.protocol.http.HttpURLConnection;

public class ConnectionImpl implements Connection{

	private ConnectionManager cm;
	private static int buffer_size = 1024;
	
	public ConnectionImpl(ConnectionManager cm) {
		this.cm = cm;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		FileOutputStream out = null;
		InputStream in = null;
		HttpURLConnection conn = null;
		try {
			in  = conn.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while( (len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			println("Read buffer Complete!");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public int getContentLength() {
		
		return 0;
	}

	@Override
	public void close() {
		
		
	}

}
