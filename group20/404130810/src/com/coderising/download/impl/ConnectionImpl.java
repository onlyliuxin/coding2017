package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {

	private HttpURLConnection httpConn;

	public ConnectionImpl(String urlStr) {
		URL url;
		try {
			url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		int length = endPos - startPos;
		byte[] rtnByte = new byte[length];
		
		RandomAccessFile raf = new RandomAccessFile("test.mp3", "rw"); 
		raf.seek(startPos);
		InputStream is = httpConn.getInputStream();
		while ((length = is.read(rtnByte)) != -1) {  
            raf.write(rtnByte, 0, length);  
        }  
		return rtnByte;
	}

	@Override
	public int getContentLength() {
		return httpConn.getContentLength();
	}

	@Override
	public void close() {
		httpConn.disconnect();
	}

}