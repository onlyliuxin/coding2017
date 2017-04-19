package com.coderising.download.api;

import java.io.IOException;
import java.net.ProtocolException;

public interface ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 */
    Connection open(String url) throws ConnectionException;
}
