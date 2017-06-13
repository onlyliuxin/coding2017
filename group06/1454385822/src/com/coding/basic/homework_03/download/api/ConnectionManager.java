package com.coding.basic.homework_03.download.api;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public Connection open(String url) throws IOException;	
}

