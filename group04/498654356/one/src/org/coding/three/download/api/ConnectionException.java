package org.coding.three.download.api;

@SuppressWarnings("serial")
public class ConnectionException extends Exception {
	
	public ConnectionException() {}
	
	public ConnectionException(String msg) {
		super(msg);
	}

	public ConnectionException(Throwable t) {
		super(t);
	}
	
}
