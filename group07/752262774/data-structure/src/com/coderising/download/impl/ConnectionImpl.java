package com.coderising.download.impl;

import com.coderising.download.api.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class ConnectionImpl implements Connection{

	private URLConnection urlConn;

	public ConnectionImpl(URLConnection urlConn) {
		this.urlConn = urlConn;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		urlConn.setAllowUserInteraction(true);
		//设置连接访问范围，当inputStream读取到endPos-1 位置时会停止
		urlConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream inputStream = urlConn.getInputStream();
		//如果用skip跳转，注意while里的条件要加上 bArrout.size() < totalLen &&  ， 不然每次循环读取都会读到最后
		//inputStream.skip(startPos);
		//int totalLen = endPos - startPos + 1;
		ByteArrayOutputStream bArrout = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int lenth = 0;

		while ((lenth = inputStream.read(buffer)) != -1) {
			bArrout.write(buffer, 0, lenth);
		}
		byte[] part = bArrout.toByteArray();
		/*
		//如果使用skip函数，则inputStream的在非最后一个线程访问read函数时，每次读取的buffer都是1024个字节，bArrout的大小有可能超出totalLen
		if(bArrout.size() > totalLen) {
			System.out.println("bArrout.size() = " + bArrout.size());
			return Arrays.copyOf(part, totalLen);
		}
		*/
		inputStream.close();
		bArrout.close();
		return part;
	}

	@Override
	public int getContentLength() {
		return urlConn.getContentLength();
	}

	@Override
	public void close() {

	}

}
