package com.coding.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.coding.array.ArrayUtil;
import com.coding.download.api.Connection;


public class ConnectionImpl implements Connection{

	private URLConnection ucon;
	
	public ConnectionImpl(String url) throws IOException{
		URL u = new URL(url); 
		ucon = u.openConnection();
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
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
	}

	@Override
	public int getContentLength() {
		return ucon.getContentLength();
	}

	@Override
	public void close() {
		//ucon = null;
	}

}
