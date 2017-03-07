package com.coding.basic.exception;

public class EmptyStackException extends RuntimeException {
	public EmptyStackException() {}
	public EmptyStackException(String msg) {
		super(msg);
	}
}
