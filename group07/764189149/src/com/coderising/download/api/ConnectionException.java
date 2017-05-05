package com.coderising.download.api;

public class ConnectionException extends Exception {
	String message;
	public ConnectionException(String message){
		super("message:"+message);
		this.message = message;
	}

}
