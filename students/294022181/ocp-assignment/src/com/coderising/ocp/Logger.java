package com.coderising.ocp;

public class Logger {
	private LogProcessor processor;
	private LogPrinter printer;
	
	public Logger(LogProcessor processor, LogPrinter printer) {
		this.processor = processor;
		this.printer = printer;
	}
	
	public void log(String msg) {
		String logMsg = msg;
		
		if (processor != null) {
			logMsg = processor.process(logMsg);
		}
		
		if (printer != null) {
			printer.print(logMsg);
		}
	}
}
