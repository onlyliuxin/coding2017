package org.wsc.coderising.download.api;

/**
 *
 * 连接池接口
 * 
 * @author Administrator
 * @date 2017年3月6日下午7:02:30
 * @version v1.0
 *
 */
public interface ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * 
	 * @param url
	 * @return
	 * @throws ConnectionException
	 */
	Connection open(String url) throws ConnectionException;

	/**
	 * 获取长度
	 * @param urlStr
	 * @return
	 * @throws ConnectionException
	 */
	int getContentLength(String urlStr) throws ConnectionException;
}
