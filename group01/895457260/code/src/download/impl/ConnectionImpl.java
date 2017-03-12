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
			init(url);
		} catch (IOException e) {
			throw new ConnectionException();
		}
	}

	private void init(String url) throws IOException {
		connection = new URL(url).openConnection();
		inputStream = new BufferedInputStream(connection.getInputStream());
		inputStream.mark(connection.getContentLength()); // 标记在开头
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		inputStream.reset(); // reset回到标记处
		skipBytes(startPos);
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

	// InputStream.skip(long)实际跳过的字节数经常小于参数值，但不会大于参数值
	private void skipBytes(long n) throws IOException {
		while (n > 0) {
			n -= inputStream.skip(n);
		}
	}
}
