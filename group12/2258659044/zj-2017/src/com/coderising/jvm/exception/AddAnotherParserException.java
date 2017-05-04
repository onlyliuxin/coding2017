package com.coderising.jvm.exception;

public class AddAnotherParserException extends Exception{

	private static final long serialVersionUID = 5171999484216739737L;

	public AddAnotherParserException(){
		super("you should add another parser to solve the unknown AttributeInfo or Constant!");
	}
}
