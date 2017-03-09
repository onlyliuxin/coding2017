package com.coderising.download.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	private HttpURLConnection connection;
	
	public ConnectionImpl(HttpURLConnection connection) {
		this.connection = connection;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		connection.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
		InputStream inputStream = new BufferedInputStream(connection.getInputStream());
		
		int totalLen = endPos - startPos;
		System.out.println(Thread.currentThread().getName()+":"+totalLen);
		int pertimeLen = 1;
		int arrayIndex = 0;
		byte[] b = new byte[totalLen];
		try {
			while (arrayIndex<totalLen && inputStream.read(b, arrayIndex, pertimeLen)!=-1) {
				arrayIndex += pertimeLen;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public int getContentLength() {
		return connection.getContentLength();
	}

	@Override
	public void close() {
		connection.disconnect();
	}

}
