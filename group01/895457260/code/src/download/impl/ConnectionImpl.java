package download.impl;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import download.api.Connection;
import download.api.ConnectionException;

public class ConnectionImpl implements Connection {
	private URLConnection connection;
	private InputStream inputStream;

	ConnectionImpl(String url) throws ConnectionException {
		try {
			connection = new URL(url).openConnection();
			inputStream = connection.getInputStream();
			inputStream.mark(connection.getContentLength());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		inputStream.reset();
		inputStream.skip(startPos);
		byte[] bytes = new byte[endPos - startPos];
		int n = inputStream.read(bytes);
		return n == -1 ? new byte[0] : bytes;
	}

	@Override
	public int getContentLength() {
		return connection.getContentLength();
	}

	@Override
	public void close() {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
