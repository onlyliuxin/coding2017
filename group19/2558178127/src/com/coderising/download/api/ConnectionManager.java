package com.coderising.download.api;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 * @throws MalformedURLException 
	 * @throws IOException 
	 */
	public Connection open(String url) throws IOException;	
	
}
