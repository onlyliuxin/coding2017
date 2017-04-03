package com.coderising.download.api;

import java.io.IOException;
import java.net.URL;

public interface Connection {

	/**
	 * 给定开始和结束位置， 读取数据， 返回值是字节数组
	 * @param startPos 开始位置， 从0开始
	 * @param endPos 结束位置
	 * @return
	 */
	public int read(byte data[]) throws IOException;
	/**
	 * 得到数据内容的长度
	 * @return
	 */
	public int getContentLength();
	
	/**
	 * 关闭连接
	 */
	public void close();
	
	public URL getURL();
	public int getStartPos();
	public int getEndPos();
}
