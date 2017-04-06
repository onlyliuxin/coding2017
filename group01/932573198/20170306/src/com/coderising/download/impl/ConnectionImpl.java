package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {

	private HttpURLConnection ucon = null;

	private URL url = null;

	public ConnectionImpl() {
	}

	public ConnectionImpl(String _url) throws MalformedURLException, IOException {
		url = new URL(_url);
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		ucon = (HttpURLConnection) url.openConnection();
		// openConnection()已将连接打开
		// 不用ucon.connect();
		ucon.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream in = ucon.getInputStream();
		
		byte[] content = new byte[endPos - startPos + 1];
		
		byte[] data = new byte[1024];
		int read = 0, i = 0;
		while ((read = in.read(data, 0, data.length)) != -1) {
			System.arraycopy(data, 0, content, i, read);
			i += read;
		}
		return content;
	}

	@Override
	public int getContentLength() {
		int length = 0;
		try {
			ucon = (HttpURLConnection) url.openConnection();
			length = ucon.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ucon.disconnect();
		}
		return length;
	}

	@Override
	public void close() {
	}

}
