package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	private static final Integer BUFFER_SIZE=1024;
	private static URLConnection nc;
	private static InputStream inputStream;
	ConnectionImpl(URL url){
		try {
			nc=url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		inputStream=nc.getInputStream();
		inputStream.skip(startPos);
		byte[] readByte=new byte[BUFFER_SIZE];
		int totalLen=endPos-startPos+1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();   
		while(baos.size()<totalLen){
			int len=inputStream.read(readByte);
			if(len<0){
				break;
			}
			baos.write(readByte,0, len); 
		}
		
		 if(baos.size() > totalLen){
	        	byte[] data = baos.toByteArray();
	        	return Arrays.copyOf(data, totalLen);
	        }
	        
			return baos.toByteArray();

	}

	@Override
	public int getContentLength() {
		return nc.getContentLength() ;
	}

	@Override
	public void close() {
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
