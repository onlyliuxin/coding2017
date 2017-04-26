package com.coding.basic.queue;

public class FullQueueException extends RuntimeException {
	
	private static final long serialVersionUID = 5782713639805661798L;

	public FullQueueException() {
        super();
    }
	
	public FullQueueException(String message) {
        super(message);
    }
}
