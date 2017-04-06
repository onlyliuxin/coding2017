package com.coderising.download.api;

public interface ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 */
	public com.coderising.download.api.Connection open(String url) throws com.coderising.download.api.ConnectionException;
}
