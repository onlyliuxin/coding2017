package com.coderising.download.api;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ConnectionException extends Exception {

	private static final long serialVersionUID = 5581807994119179835L;
	private String message;
	private Throwable t;
	private String stackTrace;
	
	public Throwable getCause(){
		return this.t;
	}
	
	public String toString(){
		return this.message;
	}
	
	public void printStackTrace() {
		System.err.print(this.stackTrace);
	}
	
	public void printStackTrace(PrintStream paramPrintStream) {
		printStackTrace(new PrintWriter(paramPrintStream));
	}
	
	public void printStackTrace(PrintWriter paramPrintWriter) {
		paramPrintWriter.print(this.stackTrace);
	}
	
	public ConnectionException(String paramString) {
		super(paramString);
		this.message = paramString;
		this.stackTrace = paramString;
	}
	
	public ConnectionException(Throwable paramThrowable) {
		super(paramThrowable.getMessage());
		this.t = paramThrowable;
		StringWriter localStringWriter = new StringWriter();
		paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
		this.stackTrace = localStringWriter.toString();
	}

	public ConnectionException(String paramString, Throwable paramThrowable) {
		super(paramString + "; nested exception is "
				+ paramThrowable.getMessage());
		this.t = paramThrowable;
		this.message = paramString;
		StringWriter localStringWriter = new StringWriter();
		paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
		this.stackTrace = localStringWriter.toString();
	}
	
}
