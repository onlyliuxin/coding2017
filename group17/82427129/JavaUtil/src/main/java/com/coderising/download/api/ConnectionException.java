package com.coderising.download.api;

public class ConnectionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8412761383909314756L;
	
	public ConnectionException() {
		super();
	}
	public ConnectionException(String arg0){
		super(arg0);
	}
	public ConnectionException(Throwable arg1){
		super(arg1);
	}
	public ConnectionException(String arg0, Throwable arg1){
		super(arg0, arg1);
	}
	
}
