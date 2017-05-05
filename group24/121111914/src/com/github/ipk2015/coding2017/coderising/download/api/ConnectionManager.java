package com.github.ipk2015.coding2017.coderising.download.api;

public interface ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 */
	public Connection open(String url) throws ConnectionException;
}
