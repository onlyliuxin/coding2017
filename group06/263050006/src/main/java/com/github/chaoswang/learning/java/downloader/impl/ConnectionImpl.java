package com.github.chaoswang.learning.java.downloader.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.github.chaoswang.learning.java.downloader.api.Connection;

public class ConnectionImpl implements Connection{

	private HttpURLConnection conn;
	private InputStream is;
	public ConnectionImpl(String url){
		initConn(url);
	}
	
	private void initConn(String url){
		try{
			URL httpUrl = new URL(url);    
	        conn = (HttpURLConnection)httpUrl.openConnection();    
	        conn.setConnectTimeout(3 * 1000);
	        conn.connect();
	        is = conn.getInputStream();
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] buffer = new byte[getContentLength()];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = is.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
	}

	@Override
	public int getContentLength() {
		int length = conn.getContentLength();
		System.out.println("length:" + length);
		return length;
	}

	@Override
	public void close() {
		try {
			if(is != null){
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
