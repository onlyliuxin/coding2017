package com.coderising.download.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private HttpURLConnection conn = null;

	public ConnectionImpl(HttpURLConnection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		conn.setRequestProperty("Range", "bytes="+ startPos + "-" + endPos);
		InputStream is = conn.getInputStream();
		byte[] buffer = new byte[endPos - startPos + 1];
		is.read(buffer, 0, endPos - startPos + 1);
		
		return buffer;
		
	}

	@Override
	public int getContentLength(){
		return conn.getContentLength();
	}

	@Override
	public void close() {
		
		if(conn != null){
			conn.disconnect();
		}
	}

}
