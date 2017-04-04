package main.week03.download.api;

import java.io.IOException;
import java.net.MalformedURLException;

public class ConnectionException extends Exception {
	
	public ConnectionException(String errmsg){
		super(errmsg);
	}

	public ConnectionException(MalformedURLException e) {
		super(e);
	}

	public ConnectionException(IOException e) {
		super(e);
	}
}
