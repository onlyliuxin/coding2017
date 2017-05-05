package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private URLConnection connection;
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
//		connection.setAllowUserInteraction(true);  
//		connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos); 
		InputStream inputstream = connection.getInputStream();
		byte[] buffer = new byte[endPos - startPos + 1];
		inputstream.skip(startPos);
		inputstream.read(buffer);
		inputstream.close();
		return buffer;
	}

	@Override
	public int getContentLength(){
		return connection.getContentLength();
	}

	@Override
	public void close() {
	}

	public void setConnection(URLConnection connection) {
		this.connection = connection;
	}
	
	

}
