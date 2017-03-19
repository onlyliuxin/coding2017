package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;

class ConnectionImpl implements Connection{
	
	private static final int  BUFF_SIZE = 1024;

	private URL url;
	
	ConnectionImpl(String _url) throws ConnectionException{
		try {
			this.url = new URL(_url);
		} catch (MalformedURLException e) {
			throw new ConnectionException(e);
		}
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws Exception {
		if(startPos > endPos){
			throw new IllegalArgumentException("startPos:"+startPos+",endPos:"+endPos);
		}
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String property = "bytes="+startPos+"-"+endPos;
		conn.setRequestProperty("RANGE", property);
		conn.connect();
		int totalLen = endPos - startPos + 1;
		InputStream is = conn.getInputStream();
		byte[] bytes = new byte[totalLen];
		byte[] buff = new byte[BUFF_SIZE];
		int len,
			count = 0;
		while((len = is.read(buff) ) > 0 && count < totalLen){
			
			System.arraycopy(buff, 0, bytes,count, len);
			count += len;
		}
		return bytes;
	}

	@Override
	public int getContentLength() {
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) url.openConnection();
			return conn.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public void close() {
		
	}

}
