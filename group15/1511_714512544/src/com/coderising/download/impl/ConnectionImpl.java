package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

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

	public synchronized byte[] read(String path, int startPos, int endPos) throws IOException {
		URL url = new URL(path);
		HttpURLConnection c = (HttpURLConnection) url.openConnection();
		c.setRequestProperty("Range",  "bytes="+startPos+"-"+endPos);
		InputStream in = connection.getInputStream();
		RandomAccessFile raf = new RandomAccessFile("d:/t.jpg","rwd");
		raf.seek(startPos);

		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) != -1) {
			raf.write(buffer, 0, len);
		}
		raf.close();
		close();
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
		InputStream in = null;
		try {
			in = connection.getInputStream();
			if(in != null){
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getUrl(){
		return connection.getURL().getPath();
	}

}
