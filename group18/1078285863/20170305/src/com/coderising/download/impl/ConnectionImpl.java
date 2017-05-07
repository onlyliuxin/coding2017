package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private URLConnection connection;
	private URL urlObj;
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		
		InputStream input = connection.getInputStream();
		
		byte[] buffer = new byte[endPos - startPos + 1];
		
		int length = 0;
		while( (length = input.read(buffer)) != -1)
		{
			bos.write(buffer, 0, endPos - startPos);
		}
		
		bos.close();
		input.close();
		
		return bos.toByteArray();
	}

	@Override
	public int getContentLength() {
		return connection.getContentLength(); 
	}

	@Override
	public void close() {
		
	}
	
	//新添加方法
	public void setConnection(URLConnection connection) {
		this.connection = connection;
		this.connection.setAllowUserInteraction(true);  
	}
	
	public void setURL(URL urlObj) {
		this.urlObj = urlObj;
	}

}
