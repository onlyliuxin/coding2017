package com.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import com.api.Connection;

public class ConnectionImpl implements Connection{

	private URL url;
	
	
	public ConnectionImpl(String urlStr) {
		super();
		try {
			this.url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		httpConn.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
		InputStream in = httpConn.getInputStream();
		byte[] buff = new byte[1024];
		int totalLen = endPos-startPos+1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while(baos.size() < totalLen){
			int len = in.read(buff);
			if(len < 0){
				break;
			}
			baos.write(buff, 0, len);
		}
		
		if(baos.size() > totalLen){
			byte[] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLen);
		}
		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		try {
			URLConnection con = url.openConnection();
			return con.getContentLength();
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
