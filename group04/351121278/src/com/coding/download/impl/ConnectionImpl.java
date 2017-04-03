package com.coding.download.impl;

import com.coding.download.api.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class ConnectionImpl implements Connection {

	private static final int THEAD_COUNT = 3;
	private URL url;
	private HttpURLConnection httpURLConnection;
	private final int BUFFER_SIZE = 1024;

	public ConnectionImpl(URL url) {
		this.url = url;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		URLConnection urlConnection = url.openConnection();
		httpURLConnection = (HttpURLConnection)urlConnection;
		httpURLConnection.setRequestMethod("GET");
		InputStream in = httpURLConnection.getInputStream();
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		int len;
		byte[] buffer = new byte[BUFFER_SIZE];
		while ((len = in.read(buffer)) != -1) {
			byteOutputStream.write(buffer, startPos, len);
		}
		return byteOutputStream.toByteArray();
	}

	@Override
	public int getContentLength() {
		return httpURLConnection.getContentLength();
	}

	@Override
	public void close() {
		httpURLConnection.disconnect();
	}

}
