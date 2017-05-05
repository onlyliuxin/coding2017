package com.bruce.homework0312.download.api;

import java.io.IOException;

public interface ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 */
	public Connection open(String url) throws ConnectionException, IOException;
}
