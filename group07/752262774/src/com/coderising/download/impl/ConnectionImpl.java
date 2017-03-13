package com.coderising.download.impl;

import com.coderising.download.api.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class ConnectionImpl implements Connection{

	private URLConnection urlConn;

	public ConnectionImpl(URLConnection urlConn) {
		this.urlConn = urlConn;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		InputStream inputStream = urlConn.getInputStream();
		inputStream.skip(startPos);
		ByteArrayOutputStream bArrout = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int lenth = 0;
		while ((lenth = inputStream.read(buffer)) != -1) {
			bArrout.write(buffer, 0, lenth);
		}
		inputStream.close();
		byte[] part = bArrout.toByteArray();
		bArrout.close();

		return part;
	}

	@Override
	public int getContentLength() {
		return urlConn.getContentLength();
	}

	@Override
	public void close() {

	}

}
