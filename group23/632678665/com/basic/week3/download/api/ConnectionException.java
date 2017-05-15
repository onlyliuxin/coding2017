package com.basic.week3.download.api;

import java.io.IOException;

public class ConnectionException extends IOException {
	public ConnectionException(){
		super();
	}
	public ConnectionException(String message){
		super(message);
	}
}
