package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	private HttpURLConnection connection;
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		this.connection.setRequestMethod("GET");
		this.connection.setReadTimeout(5000);
		this.connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

		InputStream inputstream = this.connection.getInputStream();
		byte[]b = new byte[endPos - startPos + 10];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		System.out.println("开始下载"+startPos+"-" + endPos +"---");
		int length;
		while(-1 != (length = inputstream.read(b))) {
			bos.write(b, 0 ,length);
		}


		return bos.toByteArray();
	}

	@Override
	public int getContentLength() {
				int fileSize = this.connection.getContentLength();

				System.out.println("文件大小为:"+fileSize);

				return fileSize;
	}

	@Override
	public void close() {
		this.connection.disconnect();
		
		
	}

	public ConnectionImpl(URLConnection urlconnection)
	{
			this.connection = (HttpURLConnection)urlconnection;
	}

}
