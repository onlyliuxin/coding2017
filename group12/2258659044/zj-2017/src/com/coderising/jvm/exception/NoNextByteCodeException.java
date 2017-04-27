package com.coderising.jvm.exception;

public class NoNextByteCodeException extends Exception{

	private static final long serialVersionUID = -904433109427354744L;

	public NoNextByteCodeException(int byteNum){
		super("already haven't "+byteNum+" byte data!");
	}
}
