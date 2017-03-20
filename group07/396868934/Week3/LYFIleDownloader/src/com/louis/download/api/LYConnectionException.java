package com.louis.download.api;

public class LYConnectionException extends Exception {
	private static final long serialVersionUID = 1L;
	String message;
	public LYConnectionException(String message){
		super("message:" + message);
		this.message = "message:" + message;
	}
}
