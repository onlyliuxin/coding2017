package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {

	private HttpURLConnection httpConn;

	public ConnectionImpl(String urlStr) {
		URL url;
		try {
			url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		System.out.println("Start Reading");
		System.out.println("StartPos: " + startPos);
		System.out.println("EndPos: " + endPos);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = httpConn.getInputStream();
		is.skip(startPos);

		int downloadLengh = endPos - startPos;

		byte[] b = new byte[1024];
		int total = 0;
		int len = -1;
		while ((len = is.read(b)) != -1) {
			baos.write(b, 0, len);
			total = total + len;
			if (total == downloadLengh) {
				break;
			}
		}
		is.close();
		baos.close();
		System.out.println("End Reading");
		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		return httpConn.getContentLength();
	}

	@Override
	public void close() {
		httpConn.disconnect();
	}

}