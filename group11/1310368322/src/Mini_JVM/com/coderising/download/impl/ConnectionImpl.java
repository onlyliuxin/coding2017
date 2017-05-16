package com.coderising.download.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;

public class ConnectionImpl implements Connection{
	URL url;
	static final int BUFFER_SIZE = 1024;
	
	ConnectionImpl(String _url) throws ConnectionException{
		try {
			url = new URL(_url);
		} catch (Exception e) {
			throw new ConnectionException(e);
		}
	}
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		//开始
		System.out.println("开始");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		// 设置读取的位置
		httpConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		// 从URL连接获得输入流
		InputStream is = httpConn.getInputStream();
		
		//is.skip(startPos);
		byte[] buff = new byte[BUFFER_SIZE];
		int totalLen = endPos - startPos + 1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		while(baos.size() < totalLen){
			int len = is.read(buff);
			if(len<0){
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
		URLConnection con;
		try {
			con = url.openConnection();
			return con.getContentLength();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void close() {
		
	}

}
