package com.coderising.download.impl;

import java.io.IOException;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	/**
	 * 给定开始和结束位置， 读取数据， 返回值是字节数组
	 * @param startPos 开始位置， 从0开始
	 * @param endPos 结束位置
	 * @return
	 */
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		return null;
	}
	/**
	 * 得到数据内容的长度
	 * @return
	 */
	@Override
	public int getContentLength() {
		
		return 0;
	}
	/**
	 * 关闭连接
	 */
	@Override
	public void close() {
		
		
	}

}
