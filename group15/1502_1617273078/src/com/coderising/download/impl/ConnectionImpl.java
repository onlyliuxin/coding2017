package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {
	HttpURLConnection httpURLConnection;
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] data = new byte[endPos - startPos];
		//httpURLConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream fis=httpURLConnection.getInputStream();
		fis.skip(startPos);
		fis.read(data);

		return data;
	}

	@Override
	public int getContentLength() {
		int length = httpURLConnection.getContentLength();
		return length;
	}

	@Override
	public void close() {
		httpURLConnection.disconnect();
		
	}

	public void setHttpURLConnection(HttpURLConnection httpURLConnection) {
		this.httpURLConnection = httpURLConnection;
	}

	public HttpURLConnection getHttpURLConnection() {
		return httpURLConnection;
	}
}
