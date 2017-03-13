package assignment0305.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;

import assignment0305.download.api.Connection;

public class ConnectionImpl implements Connection {
	private HttpURLConnection connection;
	private InputStream in;

	public ConnectionImpl(URL url) throws IOException {
		connection = (HttpURLConnection) url.openConnection();
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

		connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		in = connection.getInputStream();
		byte[] data = new byte[endPos - startPos + 1];

		NumberFormat num = NumberFormat.getPercentInstance();
		num.setMaximumIntegerDigits(3);
		num.setMaximumFractionDigits(2);

		int alreadyReadBytes = 0;
		while (alreadyReadBytes < data.length) {
			int byteRead = in.read(data, alreadyReadBytes, data.length - alreadyReadBytes);
			if (byteRead == -1)
				break;
			alreadyReadBytes += byteRead;
			System.out.println(Thread.currentThread().getName() + ":已完成"
					+ num.format((double) alreadyReadBytes / (double) data.length));
		}

		if (alreadyReadBytes != data.length)
			throw new IOException("Already read " + alreadyReadBytes + "bytes," + "expect " + data.length + "bytes.");
		return data;
	}

	@Override
	public int getContentLength() {
		int length = connection.getContentLength();
		return length;
	}

	@Override
	public void close() {
		try {
			in.close();
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
