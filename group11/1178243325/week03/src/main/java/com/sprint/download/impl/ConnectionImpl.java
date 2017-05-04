package com.sprint.download.impl;

import com.sprint.download.api.Connection;
import com.sprint.download.api.ConnectionException;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

public class ConnectionImpl implements Connection {

	URL url;
	static final int BUFFER_SIZE = 1024;

	ConnectionImpl(String _url) throws ConnectionException {
		try {
			url = new URL(_url);
		} catch (MalformedURLException e) {
			throw new ConnectionException(e);
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		httpConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

		InputStream is = httpConn.getInputStream();
		byte[] buff = new byte[BUFFER_SIZE];
		int totalLen = endPos - startPos + 1;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while (baos.size() < totalLen) {
			int len = is.read(buff);
			if (len < 0) {
				break;
			}
			baos.write(buff, 0, len);
		}

		if (baos.size() > totalLen) {
			byte[] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLen);
		}
		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		URLConnection con;
		try {
			con = url.openConnection();	
			return con.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void close() {
	
	}
}
