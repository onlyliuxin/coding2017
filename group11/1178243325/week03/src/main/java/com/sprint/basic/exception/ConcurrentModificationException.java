package com.coding.basic.exception;

public class ConcurrentModificationException extends RuntimeException {
	public ConcurrentModificationException() {}
	public ConcurrentModificationException(String msg) {
		super(msg);
	}
}

