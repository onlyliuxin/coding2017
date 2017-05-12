package com.coderising.teacher.litestruts;

import java.io.IOException;

import org.jdom2.JDOMException;

public class ConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

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
