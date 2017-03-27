package com.donaldy.download.impl;

import java.io.IOException;
import java.io.InputStream;

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

		System.out.println("inputStream is not equal null");

		inputStream.skip(startPos);

		int length = endPos - startPos + 1;

		System.out.println("要读的长度 : " + length);

		byte [] buffer = new byte[length];

		System.out.println("buffer - 1: " + buffer.length);

		contentLength = inputStream.read(buffer);

		System.out.println("buffer - 2: " + buffer.length);

		System.out.println("contentLength : " + contentLength);

		return buffer;
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
