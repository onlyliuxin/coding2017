package com.coding.week3.download1.api;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 */
	 Connection open(String url) throws IOException;
}
