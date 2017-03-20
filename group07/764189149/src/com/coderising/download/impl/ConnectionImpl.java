package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class ConnectionImpl implements Connection{

	private URL urlObj;
	private static final int BUFFER_SIZE = 1024;
	
	public ConnectionImpl(URL urlObj){
		this.urlObj = urlObj;
	}
	@SuppressWarnings("deprecation")
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		URLConnection connection = urlObj.openConnection();
		connection.setAllowUserInteraction(true);  
		connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos); 
		InputStream inputstream = connection.getInputStream();
		ByteOutputStream bos = new ByteOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int length = -1;
		while( -1 != (length=inputstream.read(buffer))){
			bos.write(buffer , 0 , length);
		}
		//inputstream.skip(startPos);
		bos.close();
		inputstream.close();
		return bos.toByteArray();
	}
	@Override
	public int getContentLength() {
		int length = 0;

		try {
			URLConnection connection = urlObj.openConnection();
			length = connection.getContentLength();
		} catch (IOException e) {
			System.out.println("getContentLength error:"+e.getMessage());
		}

		return length;
	}

	@Override
	public void close() {

		// TODO Auto-generated method stub
		
	}

	

}
