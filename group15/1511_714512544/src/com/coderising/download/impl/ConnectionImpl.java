package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	private HttpURLConnection  connection;
	private InputStream in;

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

	public synchronized byte[] read(int startPos, int endPos) throws IOException {
		connection.setRequestProperty("Range",  "bytes="+startPos+"-"+endPos);
		in = connection.getInputStream();
		RandomAccessFile raf = new RandomAccessFile("d:/1.png","rwd");
		raf.seek(startPos);

		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) != -1) {
			raf.write(buffer, 0, len);
		}
		return null;
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
		if(in != null){
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
