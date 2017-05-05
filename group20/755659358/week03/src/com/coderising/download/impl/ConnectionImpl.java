package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class ConnectionImpl implements Connection {

	private URL httpUrl;

	public ConnectionImpl(String url) {
		try {
			httpUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) {
		byte[] result = null;
		HttpURLConnection conn = null;
		ByteOutputStream outByte=null;
		try {
			conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5000);
			conn.addRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
			int code = conn.getResponseCode();
			System.out.println("code:" + code);
			if (code == 206) {
				InputStream in = conn.getInputStream();
				outByte = new ByteOutputStream();
				outByte.write(in);
				result=outByte.getBytes();
			}
			System.out.println("result:" + result.length);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			outByte.close();
			conn.disconnect();
		}

		System.out.println(result.length + "resultsize");
		return result;
	}

	@Override
	public int getContentLength() {
		int fileSize = 0;
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestMethod("HEAD");
			conn.setReadTimeout(5000);
			if (conn.getResponseCode() == 200) {
				fileSize = conn.getContentLength();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}

		return fileSize;
	}

	@Override
	public void close() {

	}

}
