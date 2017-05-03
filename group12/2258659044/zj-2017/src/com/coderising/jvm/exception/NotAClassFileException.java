package com.coderising.jvm.exception;

public class NotAClassFileException extends Exception{

	private static final long serialVersionUID = -3645339333237670145L;
	
	public NotAClassFileException() {
		super("this file not a java class file!");
	}

}
