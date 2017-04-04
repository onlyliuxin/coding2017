package com.coderising.download.impl;

import com.coderising.download.api.Connection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;

public class ConnectionImpl implements Connection{
	private HttpURLConnection conn = null;
	private int length = 0;

	void setConn(HttpURLConnection conn) {
		this.conn = conn;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        int hasRead; //已读取的字节数
        byte[] result = new byte[endPos - startPos + 1];
        byte[] buffer = new byte[1024];
		if (HttpURLConnection.HTTP_PARTIAL == conn.getResponseCode()){
			InputStream is = conn.getInputStream();
			while(length < result.length && (hasRead = is.read(buffer)) != -1){
				System.arraycopy(buffer, 0, result, length, hasRead);
				length += hasRead;
			}
			is.close();
		}

		return result;
	}

	@Override
	public int getContentLength() {
		int length = 0;
		try {
			if(HttpURLConnection.HTTP_OK == conn.getResponseCode()){
				length = conn.getContentLength();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return length;
	}

	@Override
	public void close() {
		conn.disconnect();
	}

}
