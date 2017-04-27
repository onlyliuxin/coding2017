package com.coding.week3.download1.impl;

import com.coding.week3.download1.api.Connection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;


public class ConnectionImpl implements Connection {

	private HttpURLConnection httpURLConnection;

	public ConnectionImpl() {
	}

	public ConnectionImpl(HttpURLConnection httpURLConnection) {
		this.httpURLConnection = httpURLConnection;
	}


	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		if (startPos < 0 || endPos < 0 || endPos > getContentLength()) {
			throw new IndexOutOfBoundsException();
		}
		if (startPos >= endPos) {
			throw new IllegalArgumentException();
		}
		byte[] bytes = new byte[endPos - startPos + 1];

		InputStream inputStream = httpURLConnection.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		bis.read(bytes, startPos ,bytes.length-1);
		return bytes;
	}


	@Override
	public int getContentLength() {
		return httpURLConnection.getContentLength();
	}

	@Override
	public void close() {
		httpURLConnection.disconnect();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return httpURLConnection.getInputStream();
	}

}
