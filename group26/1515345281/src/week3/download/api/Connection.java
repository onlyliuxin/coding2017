package week3.download.api;

import java.io.IOException;

public interface Connection {

	/**
	 * 关闭连接
	 */
	void close();
	
	/**
	 * 得到数据内容的长度
	 * @return
	 */
	int getContentLength();
	
	/**
	 * 给定开始和结束位置， 读取数据， 返回值是字节数组
	 * @param startPos 开始位置， 从0开始
	 * @param endPos 结束位置
	 * @return
	 */
	byte[] read(int startPos,int endPos) throws IOException;
}
