package download.api;

import java.io.IOException;

public interface Connection {
	/**
	 * 读取字节
	 * @param bytes 存放读出的字节
	 * @return 实际读出的字节数
	 */
	int read(byte[] bytes) throws IOException;
	/**
	 * 得到数据内容的长度
	 * @return
	 */
	int getContentLength();

	/**
	 * 恢复到初始状态，可以重新读字节
	 */
	void reset() throws IOException;
	
	/**
	 * 关闭连接
	 */
	void close();
}
