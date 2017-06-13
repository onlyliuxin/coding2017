package com.github.eloiseSJTU.coding2017.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import com.github.eloiseSJTU.coding2017.download.api.Connection;

public class ConnectionImpl implements Connection {

	private URLConnection urlConnection;

	public ConnectionImpl(URLConnection urlConnection) {
		this.urlConnection = urlConnection;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		if (startPos > endPos) {
			return null;
		}
		byte[] result = new byte[endPos - startPos + 1];
		if (urlConnection != null) {
			urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
			InputStream inputStream = urlConnection.getInputStream();
			int len = 0;
			int offset = 0;
			while ((len = inputStream.read(result, offset, result.length - offset)) != -1) {
				offset += len;
			}
			inputStream.close();
		}
		return result;
	}

	@Override
	public int getContentLength() {
		if (urlConnection != null) {
			return urlConnection.getContentLength();
		}
		return -1;
	}

	@Override
	public void close() {
		urlConnection = null;
	}

}
