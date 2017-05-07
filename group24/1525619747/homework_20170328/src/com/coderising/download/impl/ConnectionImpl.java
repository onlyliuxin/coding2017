package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection
{

	private String urlStr = null;

	public ConnectionImpl () {
	}

	public ConnectionImpl (String url) {
		this.urlStr = url;
	}

	public byte[] read(int startPos, int endPos) throws IOException {
		// 设置起始与终止位置
		URL url = null;
		try {
			url = new URL(this.urlStr);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		conn.setConnectTimeout(3 * 1000); // 3s
		conn.setReadTimeout(60 * 1000);
		conn.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

		InputStream inputStream = conn.getInputStream();

		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}

		inputStream.close();
		bos.close();
		conn.disconnect();

		return bos.toByteArray();
	}

	public int getContentLength() {
		URL url = null;
		try {
			url = new URL(this.urlStr);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		conn.setConnectTimeout(3 * 1000); // 3s
		conn.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		return conn.getContentLength();
	}

	public void close() {

	}

}
