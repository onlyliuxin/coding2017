package com.coderising.download.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {
	HttpURLConnection urlcon = null;

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
		byte[] bytes = new byte[endPos - startPos + 1];

		InputStream is = new BufferedInputStream(urlcon.getInputStream());

		// Read in the bytes
		int offset = startPos;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		
		System.out.println("start is: " + startPos);
		
//		for (int i=0; i<bytes.length; i++) {
//			System.out.print(bytes[i] + ", ");
//		}
//		System.out.println(bytes.length);
		return bytes;
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
