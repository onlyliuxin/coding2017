package com.github.wdn.coding2017.coderising.download.api;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface Connection {
	/**
	 * 给定开始和结束位置， 读取数据， 返回值是字节数组
	 * @param startPos 开始位置， 从0开始
	 * @param endPos 结束位置
	 * @return
	 */
	public byte[] read(int startPos,int endPos) throws IOException;
	/**
	 * 得到数据内容的长度
	 * @return
	 */
	public int getContentLength();
	
	/**
	 * 关闭连接
	 */
	public void close();


	void setHttpURLConnection(HttpURLConnection httpURLConnection);
}
