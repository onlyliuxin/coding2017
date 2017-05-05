package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;

public class ConnectionImpl implements Connection {

	private HttpURLConnection conn;
	
	public ConnectionImpl(){}
	public ConnectionImpl(String url) throws ConnectionException {
		try {
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
		} catch (IOException e) {
			throw new ConnectionException();
		} 
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		conn.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
		int code = conn.getResponseCode();
		ByteArrayOutputStream bos = null;
		if (code == 200 || code == 206) {
			InputStream is = conn.getInputStream();
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.close();
			return bos.toByteArray();
		} else {
			System.out.println("下载失败");
			return  null;
		}
		
	}

	@Override
	public int getContentLength() {
		return conn.getContentLength();
	}
	@Override
	public void close() {
		conn = null;
	}

}
