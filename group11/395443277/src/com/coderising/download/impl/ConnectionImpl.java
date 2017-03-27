package com.coderising.download.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {
	HttpURLConnection urlcon = null;
	static final int BUFFER_SIZE = 1024;

	public ConnectionImpl(String url) {
		try {
			URL imgUrl = new URL(url);
			urlcon = (HttpURLConnection) imgUrl.openConnection();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		urlcon.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

		InputStream is = urlcon.getInputStream();

		byte[] buff = new byte[BUFFER_SIZE];

		int totalLength = endPos - startPos + 1;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		while (baos.size() < totalLength) {
			int len = is.read(buff);
			if (len < 0) {
				break;
			}
			baos.write(buff, 0, len);
		}

		if (baos.size() > totalLength) {
			byte[] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLength);
		}

		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		String contentLength = urlcon.getHeaderField("content-Length");

		if (contentLength != null) {
			return Integer.parseInt(contentLength);
		}

		return 0;
	}

	@Override
	public void close() {
		// try {
		// is.close();
		// System.out.println("one connection is closed");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

}
