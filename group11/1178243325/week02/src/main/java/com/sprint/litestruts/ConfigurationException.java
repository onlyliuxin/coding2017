package com.sprint.litestruts;

import java.io.IOException;
import org.jdom2.JDOMException;
public class ConfigurationException extends RuntimeException {
	
	public ConfigurationException(String msg) {
		super(msg);
	} 

	public ConfigurationException(JDOMException e) {
		super(e);
	}

	public ConfigurationException(IOException e) {
		super(e);
	}
}
