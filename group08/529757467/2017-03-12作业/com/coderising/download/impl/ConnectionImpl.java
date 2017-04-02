package com.coderising.download.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {
	private HttpURLConnection httpURLConnection;

	public ConnectionImpl(String url) throws MalformedURLException, IOException {
		httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		httpURLConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		System.out.println("开始：" + startPos + "-----结束:" + endPos);
		RandomAccessFile raf = new RandomAccessFile(new File("D:\\aaa.jpg"), "rw");
		raf.seek(startPos);
		BufferedInputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
		int length = 0;
		byte[] b = new byte[1024];
		while ((length = is.read(b, 0, b.length)) != -1) {
			raf.write(b, 0, length);
		}
		raf.close();
		return null;
	}

	@Override
	public int getContentLength() {
		return httpURLConnection.getContentLength();
	}

	@Override
	public void close() {
	}

}
