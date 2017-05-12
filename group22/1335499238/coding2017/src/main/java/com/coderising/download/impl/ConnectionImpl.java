package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;


public class ConnectionImpl implements Connection{

	private URLConnection urlConnection;
	private URL url;
	public ConnectionImpl(String url){
		try {
			this.url = new URL(url);
			urlConnection = this.url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		URLConnection uc = url.openConnection();
		uc.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream inputStream = uc.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length = 0;
		while((length = inputStream.read(buffer)) != -1){
			baos.write(buffer, 0, length);
		}
		inputStream.close();
		baos.close();
		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		return urlConnection.getContentLength();
	}

	@Override
	public void close() {
		
	}

}
