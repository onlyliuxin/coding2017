package com.multiThreadDownload.impl;

import com.multiThreadDownload.api.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class ConnectionImpl implements Connection{
	private HttpURLConnection conn ;
	public ConnectionImpl(HttpURLConnection conn) {
		this.conn = conn;
	}
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		conn.setConnectTimeout(5000);
		
		InputStream is = conn.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int length = 0;
		byte[] buffer = new byte[1024];
		while(-1 != ( length = is.read(buffer))){
			bos.write(buffer,0,length);
		}
		bos.flush();
		is.close();
		bos.close();
		
		
		return bos.toByteArray();
	}

	@Override
	public int getContentLength() {
		
		return conn.getContentLength();
	}

	@Override
	public void close() {
		if(conn!=null){
			conn = null;
		}
	}

}
