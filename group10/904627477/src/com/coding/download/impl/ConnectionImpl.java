package com.coding.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.coding.download.api.Connection;
import com.coding.download.api.ConnectionException;


public class ConnectionImpl implements Connection{

	private URL u;
	private final static int BUFFER_SIZE = 1024;
	
	public ConnectionImpl(String url) throws ConnectionException{
		try {
			u = new URL(url);
		} catch (MalformedURLException e) {
			throw new ConnectionException(e);
		} 
	}

	@Override
	/*public byte[] read(int startPos, int endPos) throws IOException {
		URLConnection ucon = u.openConnection();
		ucon.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream in = ucon.getInputStream();
		byte[] result = new byte[0];
		byte[] buff = new byte[1024];
		int len = 0;
		while((len=in.read(buff))!=-1){
			int rLen = result.length;
			result =ArrayUtil.grow(result, len);
			System.arraycopy(buff, 0, result, rLen, len);
		}
		return result;
	}*/	
	public byte[] read(int startPos, int endPos) throws IOException {
		URLConnection ucon = u.openConnection();
		ucon.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream in = ucon.getInputStream();
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buff = new byte[BUFFER_SIZE];
		int len = 0;
		while((len=in.read(buff))!=-1){
			result.write(buff, 0, len);
		}
		return result.toByteArray();
	}

	@Override
	public int getContentLength() {
		try {
			URLConnection ucon = u.openConnection();
			return ucon.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void close() {
		//ucon = null;
	}

}
