package com.coderising.download.api;

public interface ConnectionManager {
	/**
	 * ����һ�� URL����һ������
	 * @param url
	 * @return
	 */
	public Connection open(String url) throws ConnectionException;
}
