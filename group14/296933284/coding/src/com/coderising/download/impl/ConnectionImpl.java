package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {
	private HttpURLConnection httpURLConnection = null;
	private int contentLength = 0;
	private int responsecode = 0;

	public ConnectionImpl(HttpURLConnection urlConnection) {
		this.httpURLConnection = urlConnection;
	}

	public int getResponsecode() {
		try {
			responsecode = httpURLConnection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responsecode;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		httpURLConnection.setConnectTimeout(5000);

		byte[] bytes = null;
		if (getResponsecode() == 206) {

			InputStream inputStream = httpURLConnection.getInputStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(endPos - startPos);

			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}

			bytes = outputStream.toByteArray();

			outputStream.close();
			inputStream.close();
		}

		return bytes;
	}

	@Override
	public int getContentLength() {
		InputStream inputStream = null;

		try {
			inputStream = httpURLConnection.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				contentLength += len;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return contentLength;
	}

	@Override
	public void close() {

	}

}
