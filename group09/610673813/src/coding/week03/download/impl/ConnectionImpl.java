package coding.week03.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import coding.week03.download.api.Connection;


public class ConnectionImpl implements Connection {
	private static final int BUFFER_SIZE = 1024;
	private HttpURLConnection connection;
	private ByteArrayOutputStream outputStream;
	private InputStream inputstream;

	public ConnectionImpl(HttpURLConnection connection) {
		this.connection=connection;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		inputstream = connection.getInputStream();
		outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int length = 0;
		while (inputstream.read(buffer) != -1) {
			outputStream.write(buffer, 0, length);
		}
		return outputStream.toByteArray();
	}

	@Override
	public int getContentLength() {
		return connection.getContentLength();
	}

	@Override
	public void close() {
		try {
			inputstream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
