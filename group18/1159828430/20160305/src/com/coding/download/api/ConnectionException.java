package com.coding.download.api;

public class ConnectionException extends Exception {
	public ConnectionException() {
		System.out.println("未知错误");
	}
	
	public ConnectionException(String msg) {
		System.out.println(msg);
	}
}
