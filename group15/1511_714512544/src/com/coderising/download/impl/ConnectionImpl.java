package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	URL url = null;
	private static final int BUFFER_SIZE = 1024;

	public ConnectionImpl(String _url) {
		try {
			this.url = new URL(_url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给定开始和结束位置， 读取数据， 返回值是字节数组
	 * @param startPos 开始位置， 从0开始
	 * @param endPos 结束位置
	 * @return
	 */
	@Override

	public byte[] read(int startPos, int endPos) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Range",  "bytes="+startPos+"-"+endPos);  //重要

		InputStream is=  conn.getInputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int targetLen = endPos-startPos+1;

		while (bos.size() < targetLen){
			int len = is.read(buffer);
			if(len < 0){
				break;
			}
			bos.write(buffer, 0, len);
		}

		if(bos.size() > targetLen){
			byte[] result = bos.toByteArray();
			return Arrays.copyOf(result, targetLen);
		}
		return bos.toByteArray();
	}
	/**
	 * 得到数据内容的长度
	 * @return
	 */
	@Override
	public int getContentLength() {
		HttpURLConnection conn;

		try {
			conn = (HttpURLConnection) url.openConnection();
			return conn.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;

	}
	/**
	 * 关闭连接
	 */
	@Override
	public void close() {

	}

}
