package com.coderising.litestruts;

import java.io.IOException;

import org.jdom2.JDOMException;

//为什么要写这个异常类？？
public class ConfigurationException extends RuntimeException{
	
	public ConfigurationException(String msg){
		super(msg);
	}
	
	public ConfigurationException(JDOMException e){
		super(e);
	}
	
	public ConfigurationException(IOException e){
		super(e);
	}
}
