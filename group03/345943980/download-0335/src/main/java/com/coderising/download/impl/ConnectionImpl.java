package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {

	private HttpURLConnection urlCon;
	private InputStream is;
	private ByteArrayOutputStream os;

	public ConnectionImpl(HttpURLConnection urlCon) throws IOException {
		this.urlCon = urlCon;
	}

	public byte[] read(int startPos, int endPos) throws IOException {
		is = urlCon.getInputStream();
		os = new ByteArrayOutputStream();
		byte[] buffer = new byte[1000];
		while (is.read(buffer) != -1) {
			os.write(buffer);
		}
		return os.toByteArray();
	}

	public int getContentLength() {
		return urlCon.getContentLength();
	}

	public void close() {
		try {
			if (is != null) {
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
