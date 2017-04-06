package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	URL url;
	static final int BUFFER_SIZE = 1024;
	
	ConnectionImpl (String _url){
		try {
			url = new URL(_url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		
		InputStream is = httpConn.getInputStream();
		
		byte[] buff= new byte[BUFFER_SIZE];
		int totalLen = endPos - startPos +1;
		ByteArrayOutputStream  baos = new ByteArrayOutputStream();
		while(baos.size() < totalLen){
			int len = is.read(buff);
			if(len < 0){
				break;
			}
			baos.write(buff,0,len);
		}
		if(baos.size() > totalLen){
			byte[] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLen);
		}
		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		URLConnection conn = null;
		try {
			conn = url.openConnection();
			return conn.getContentLength();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void close() {
		
		
	}

}
