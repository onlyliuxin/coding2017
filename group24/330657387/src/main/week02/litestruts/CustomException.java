package main.week02.litestruts;

import java.io.IOException;

import org.jdom2.JDOMException;

public class CustomException extends RuntimeException {
	
	public CustomException(IOException e) {
		super(e.getMessage());
	}

	public CustomException(JDOMException e) {
		super(e.getMessage());
	}

	public CustomException(String msg) {
		super(msg);
	}

}
