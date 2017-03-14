package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;

import com.coderising.download.api.Connection;


public class ConnectionImpl implements Connection{

	private URLConnection connection;
	InputStream inputStream;
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
	    inputStream = this.connection.getInputStream();  
		byte[] buffer = new byte[endPos - startPos + 1];
		inputStream.skip(startPos);
		inputStream.read(buffer);
		
		return buffer;
	}

	@Override
	public int getContentLength() {
		
		return connection.getContentLength();
	}

	@Override
	public void close() {
		try {
			this.inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setConnection(URLConnection connection) {
		this.connection = connection;
	}

}
