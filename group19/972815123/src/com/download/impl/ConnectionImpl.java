package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private URLConnection connect;
	public void setHttpURLConnection(URLConnection conn){
		this.connect = conn;
		this.connect.setRequestProperty("Connection", "Keep-Alive");
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException  {
		
		InputStream is = connect.getInputStream();
		is.skip(startPos);
		
		byte[] buffer = new byte[1024];
		int hasRead = 0;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while(startPos < endPos && (hasRead = is.read(buffer, 0, 1024)) != -1){
			out.write(buffer, 0, hasRead);
			startPos += hasRead;
		}
		
		byte[] result = out.toByteArray();
		return result;
	}

	@Override
	public int getContentLength() {
		return connect.getContentLength();
	}

	@Override
	public void close() {
	}

}
