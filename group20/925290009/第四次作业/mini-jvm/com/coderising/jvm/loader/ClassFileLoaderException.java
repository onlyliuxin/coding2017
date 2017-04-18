package com.coderising.jvm.loader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ClassFileLoaderException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassFileLoaderException(String msg){
		super(msg);
	}
	
	public ClassFileLoaderException(FileNotFoundException e){
		super(e);
	}
	
	public ClassFileLoaderException(IOException e){
		super(e);
	}

}
