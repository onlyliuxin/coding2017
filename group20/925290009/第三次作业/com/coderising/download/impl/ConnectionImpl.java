package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private HttpURLConnection httpConn;
	private InputStream inputStream;
	
	public ConnectionImpl(HttpURLConnection httpconn) {
		// TODO Auto-generated constructor stub
		this.httpConn = httpconn;
	}

	
	//read() 方法有问题
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		inputStream = httpConn.getInputStream();
		
		System.out.println(startPos + "----" + endPos);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] by = new byte[1024];
		
		while((len = inputStream.read(by))!= -1){
			byteArrayOutputStream.write(by, 0, len);
		}
		System.out.println(byteArrayOutputStream.toByteArray().length);
		return byteArrayOutputStream.toByteArray();

	}

	@Override
	public int getContentLength() {
		if (httpConn != null) {
			return httpConn.getContentLength();
		}
		return 0;
	}

	@Override
	public void close() {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (httpConn != null) {
			httpConn.disconnect();
		}
		
	}

}
