package com.pxshuo.se03.download.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;

import com.pxshuo.se03.download.api.Connection;

class ConnectionImpl implements Connection{

	private URL url;
	private HttpURLConnection connect = null;
	private int length;//不知道每次都确定一下是否比较好
	
	private static int BUFFER_SIZE = 1024;
	
	/**
	 * 初始化
	 * @param destUrl
	 */
	public ConnectionImpl(String destUrl) {
		// TODO Auto-generated constructor stub
		
		try {
			url = new URL(destUrl);
			length = -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		connect = (HttpURLConnection)url.openConnection();
		connect.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		
		InputStream is = connect.getInputStream();
		
		byte[] buff = new byte[BUFFER_SIZE];
		int totalLength = endPos - startPos + 1;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		while(baos.size() < totalLength){
			int len = is.read(buff);
			if (len < 0) {
				break;
			}
			baos.write(buff,0,len);
		}
		
		if (baos.size() > totalLength) {
			byte[] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLength);
		}

		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		int length = -1;
        try {
        	connect = (HttpURLConnection) url.openConnection();
			connect.setRequestMethod("GET");
			connect.setConnectTimeout(10000);
			if (connect.getResponseCode() == 200) {
				length = connect.getContentLength();
			}
			System.out.println("error:" + length);
			connect.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return length;
	}

	@Override
	public void close() {
		if (connect != null) {
			connect.disconnect();
		}
	}

}
