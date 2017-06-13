package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private static URLConnection nc;
	private static InputStream inputStream;
	
	ConnectionImpl(URL url){
		try {
			nc=url.openConnection();
			inputStream=nc.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		inputStream.skip(startPos);
		byte[] readByte=new byte[endPos-startPos];
		inputStream.read(readByte);
		return readByte;
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
