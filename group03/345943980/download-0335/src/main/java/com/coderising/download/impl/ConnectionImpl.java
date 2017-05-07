package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {

	private HttpURLConnection httpConn;
	private InputStream is;
	private ByteArrayOutputStream baos;
	static final int BUFFER_SIZE = 1024;

	public ConnectionImpl(HttpURLConnection httpConn) throws IOException {
		this.httpConn = httpConn;
	}

	public byte[] read(int startPos, int endPos) throws IOException {

		httpConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

		is = httpConn.getInputStream();

		baos = new ByteArrayOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];

		int totalLen = endPos - startPos + 1;

		while (baos.size() < totalLen) {

			int len = is.read(buffer);
			if (len < 0) {
				break;
			}
			baos.write(buffer, 0, len);
		}
		
		if(baos.size() > totalLen){
        	byte[] data = baos.toByteArray();
        	return Arrays.copyOf(data, totalLen);
        }
		
		return baos.toByteArray();
	}

	public int getContentLength() {

		return httpConn.getContentLength();
	}

	public void close() {

	}
}
