package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private HttpURLConnection httpURLConnection;
	private InputStream inputStream;

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		httpURLConnection.setRequestProperty("Range","bytes="+startPos+"-"+endPos);
		InputStream inputStream = httpURLConnection.getInputStream();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] bytes = new byte[1024];
		int read = 0;
		while ((read=inputStream.read(bytes))!=-1){
			byteArrayOutputStream.write(bytes,0,read);
		}
		byteArrayOutputStream.flush();
		return byteArrayOutputStream.toByteArray();
	}

	@Override
	public int getContentLength() {
		
		return httpURLConnection.getContentLength();
	}

	@Override
	public void close() {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setHttpURLConnection(HttpURLConnection httpURLConnection) {
		this.httpURLConnection = httpURLConnection;
	}
}