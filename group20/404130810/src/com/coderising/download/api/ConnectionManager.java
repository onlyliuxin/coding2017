package com.coderising.download.api;

public interface ConnectionManager {
	public Connection open(String url) throws ConnectionException;
}
