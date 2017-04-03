package com.coderising.download.api;

public interface ConnectionManager {
	/**
	 * 缁欏畾涓�涓猽rl , 鎵撳紑涓�涓繛鎺�
	 * @param url
	 * @return
	 */
	public Connection open(String url) throws ConnectionException;	
}