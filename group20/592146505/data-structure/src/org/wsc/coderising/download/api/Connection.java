package org.wsc.coderising.download.api;

import java.io.IOException;

/**
 * 连接接口
 *
 * @author Administrator
 * @date 2017年3月6日下午7:00:53
 * @version v1.0
 *
 */
public interface Connection {
	/**
	 * 给定开始和结束位置， 读取数据， 返回值是字节数组
	 * 
	 * @param startPos
	 *            开始位置， 从0开始
	 * @param endPos
	 *            结束位置
	 * @return
	 * @throws IOException
	 * @throws ConnectionException 
	 */
	public byte[] read(int startPos, int endPos) throws IOException, ConnectionException;

	/**
	 * 得到数据内容的长度
	 * 
	 * @return
	 */
	public int getContentLength();
	
	/**
	 * 获取文件名称
	 * @return
	 */
	public String getFileName();

	/**
	 * 关闭连接
	 */
	public void close();
}
