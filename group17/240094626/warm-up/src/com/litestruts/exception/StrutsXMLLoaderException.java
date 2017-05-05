package com.coderising.litestruts.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class StrutsXMLLoaderException extends Exception {

	private static final long serialVersionUID = 5481955299021871754L;
	private String message;
	private String stackTrace;
	private Throwable t;
	public Throwable getCause() {
		return this.t;
	}

	public String toString() {
		return getMessage();
	}

	public String getMessage() {
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

	public StrutsXMLLoaderException(String paramString) {
		super(paramString);
		this.message = paramString;
		this.stackTrace = paramString;
	}
	
	public StrutsXMLLoaderException(Throwable paramThrowable) {
		super(paramThrowable.getMessage());
		this.t = paramThrowable;
		StringWriter localStringWriter = new StringWriter();
		paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
		this.stackTrace = localStringWriter.toString();
	}

	public StrutsXMLLoaderException(String paramString, Throwable paramThrowable) {
		super(paramString + "; nested exception is "
				+ paramThrowable.getMessage());
		this.t = paramThrowable;
		StringWriter localStringWriter = new StringWriter();
		paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
		this.stackTrace = localStringWriter.toString();
	}
}
