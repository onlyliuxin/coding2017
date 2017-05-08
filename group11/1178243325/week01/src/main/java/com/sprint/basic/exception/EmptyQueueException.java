package com.sprint.basic.exception;

public class EmptyQueueException extends RuntimeException {
	public EmptyQueueException() {}
	public EmptyQueueException(String msg) {
		super(msg);
	} 
}
