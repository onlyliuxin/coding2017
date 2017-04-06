package week03.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import week03.download.api.Connection;

public class ConnectionImpl implements Connection {
	private String url;
	private HttpURLConnection connection;

	public ConnectionImpl(String url) {
		this.url = url;
		initConnection();
	}

	/**
	 * 获取HttpURLConnection
	 */
	private void initConnection() {
		try {
			URL targetUrl = new URL(url);
			connection = (HttpURLConnection) targetUrl.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

		connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream in = connection.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int len = 0;
		byte[] rs = new byte[1024];
		while ((len = in.read(rs)) != -1) {
			out.write(rs, 0, len);
		}
		out.close();
		in.close();
		return out.toByteArray();

	}

	@Override
	public int getContentLength() {
		return connection.getContentLength();
	}

	@Override
	public void close() {
		connection.disconnect();
	}
}
