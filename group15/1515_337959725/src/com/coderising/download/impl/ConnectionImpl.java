package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private URL url;
	

	public ConnectionImpl(URL url) {
		this.url = url;
	}
	

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] b=new byte[1024*1024*10];
		InputStream is = url.openConnection().getInputStream();
		is.skip(startPos);
		while((is.read(b,0, endPos-startPos))!=-1);
		return b;
	}

	@Override
	public int getContentLength() {
		int length=0;
		try {
			byte[] b=new byte[1024*1024*10];
			 InputStream is = url.openConnection().getInputStream();
			while((length = is.read(b, 0, 1024*1024*10))!=-1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return length;
	}

	@Override
	public void close() {
		
		
	}

}
