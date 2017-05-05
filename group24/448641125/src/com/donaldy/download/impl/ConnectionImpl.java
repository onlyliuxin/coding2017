package com.donaldy.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.donaldy.download.api.Connection;

public class ConnectionImpl implements Connection{

	int contentLength;

	InputStream inputStream;

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public void setInputStream (InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

		if (inputStream == null)
			return null;

		inputStream.skip(startPos);

		int totalLen = endPos - startPos + 1;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte [] buffer = new byte[1024];

		while (baos.size() < totalLen) {
			int len = inputStream.read(buffer);
			if (len < 0)
				break;
			baos.write(buffer, 0, len);
		}

		if (baos.size() > totalLen) {
			byte [] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLen);
		}

		System.out.println("读入：" + totalLen);

		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		return contentLength;
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
