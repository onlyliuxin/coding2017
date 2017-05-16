package com.coding.basic.queue;

public class EmptyQueueException extends RuntimeException {
	
	private static final long serialVersionUID = 7013504079461713464L;

	public EmptyQueueException() {
        super();
    }
	
	public EmptyQueueException(String message) {
        super(message);
    }
}
