package litestruts;

import java.io.IOException;

import org.dom4j.DocumentException;


public class ConfigurationException extends RuntimeException {


	public ConfigurationException(String msg) {
		super(msg);
	}


	public ConfigurationException(IOException e) {
		super(e);
	}


	public ConfigurationException(DocumentException e) {
		super(e);
	}
	

}
