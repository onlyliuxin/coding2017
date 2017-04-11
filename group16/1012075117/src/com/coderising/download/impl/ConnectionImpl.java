package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {
	private HttpURLConnection httpURLConnection;
	private InputStream inputstream;
	String url;

	public ConnectionImpl(HttpURLConnection httpURLConnection, String url) {
		this.httpURLConnection = httpURLConnection;
		this.url = url;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		inputstream = httpURLConnection.getInputStream();
		int total = endPos - startPos + 1;
		inputstream.skip(startPos);
		byte[] bytes = new byte[total];
		int len = 0;
		int hasRead = 0;
		while ((len = inputstream.read(bytes, hasRead, total - hasRead)) > 0) {
			hasRead = hasRead + len;
		}
		return bytes;
	}

	@Override
	public int getContentLength() {
		return httpURLConnection.getContentLength();
	}

	@Override
	public void close() {
		try {
			if (inputstream != null)
				inputstream.close();
			httpURLConnection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getFileName() {
		int index;
		String fileName = "src/com/coderising/download/";
		String temp = httpURLConnection.getHeaderField("Content-Disposition");
		if (temp != null) {
			index = temp.indexOf("=");
			fileName += temp.substring(index + 2, temp.length() - 1);
			return fileName;
		} else {
			index = url.lastIndexOf("/");
			fileName += url.substring(index + 1);
			return fileName;
		}
	}

}