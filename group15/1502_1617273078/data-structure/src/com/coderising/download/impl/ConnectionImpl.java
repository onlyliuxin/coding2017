package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {
	HttpURLConnection httpURLConnection;
	static final int BUFFER_SIZE = 1024;
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		//byte[] data = new byte[endPos - startPos];
		//httpURLConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream fis=httpURLConnection.getInputStream();
		fis.skip(startPos);
		byte[] buff = new byte[BUFFER_SIZE];
		int totalLen = endPos - startPos + 1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while (baos.size() < totalLen) {
			int len = fis.read(buff);
			if (len < 0) {
				break;
			}
			baos.write(buff,0,len);
		}
		if (baos.size() > totalLen) {
			byte[] datas = baos.toByteArray();
			return Arrays.copyOf(datas, totalLen);
		}


		return baos.toByteArray();
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
