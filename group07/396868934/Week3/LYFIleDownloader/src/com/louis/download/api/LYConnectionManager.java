package com.louis.download.api;

public interface LYConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 */
	public LYConnection open(String url) throws LYConnectionException;
}
