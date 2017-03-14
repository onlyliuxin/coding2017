package com.coderising.download.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private static final int  BUFF_LENGTH = 1024;

	private HttpURLConnection conn;
	
	public ConnectionImpl(HttpURLConnection conn) {
		this.conn = conn;
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws Exception {
		if(startPos > endPos){
			throw new IllegalArgumentException("startPos:"+startPos+",endPos:"+endPos);
		}
		String property = "bytes="+startPos+"-"+endPos;
		conn.setRequestProperty("RANGE", property);
		if(conn.getResponseCode() == 206){
			InputStream is = conn.getInputStream();
			byte[] bytes = new byte[endPos-startPos];
			byte[] buff = new byte[BUFF_LENGTH];
			int len,i=0;
			while((len = is.read(buff) ) != 0){
				System.arraycopy(buff, 0, bytes, i*len, len);
				i++;
			}
			return bytes;
		}
		return null;
	}

	@Override
	public int getContentLength() {
		return conn.getContentLength();
	}

	@Override
	public void close() {
		
	}

}
