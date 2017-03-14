package org.coding.three.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import org.coding.three.download.api.Connection;


public class ConnectionImpl implements Connection{
	private String url;
	private int length;
	private InputStream inputStream;
	public ConnectionImpl (String url) throws Exception{
		super();
		this.url = url;
		URL u = new URL(url);
		int length = u.openConnection().getContentLength();
		this.length = length;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		URL u = new URL(this.url);
		HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setConnectTimeout(5000);
		// ***
		urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		System.out.println(Thread.currentThread() + "---> startPos = " + startPos + ", endPos = " + endPos );
		int size = endPos - startPos + 1;
		byte[] data = new byte[size * 2];
		InputStream inputStream = urlConnection.getInputStream();
		this.inputStream = inputStream;
		// *** 从 0 开始
		byte[] b = new byte[1024];
		int length = 0;
		int count = 0;
		int dataLength = 0;
		while((length = inputStream.read(b)) != -1) {
			int destPos = count * 1024;
			for(int i = 0; i < length; i++) {
				data[destPos + i] = b[i];
				dataLength++;
			}
			count++;
		}
		return Arrays.copyOf(data, dataLength);
	}

	@Override
	public int getContentLength() {
		return this.length;
	}

	@Override
	public void close() {
		try {
			this.inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
