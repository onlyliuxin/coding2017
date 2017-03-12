package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	private HttpURLConnection  connection;

	public ConnectionImpl(HttpURLConnection connection) {
		this.connection = connection;
	}

	/**
	 * 给定开始和结束位置， 读取数据， 返回值是字节数组
	 * @param startPos 开始位置， 从0开始
	 * @param endPos 结束位置
	 * @return
	 */
	@Override

	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] buffer = new byte[endPos-startPos+1];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		connection.setRequestProperty("Range",  "bytes="+startPos+"-"+endPos);
		int res = connection.getResponseCode();
		if(res == 206){ //下载部分内容请求成功
			InputStream in = connection.getInputStream();

			int len = 0;
			byte[] b = new byte[1024];
			while ((len = in.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			buffer = bos.toByteArray();
		}

		return buffer;
	}
	/**
	 * 得到数据内容的长度
	 * @return
	 */
	@Override
	public int getContentLength() {
		return connection.getContentLength();
	}
	/**
	 * 关闭连接
	 */
	@Override
	public void close() {
		InputStream in;
		try {
			in = connection.getInputStream();
			if(in != null){
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
