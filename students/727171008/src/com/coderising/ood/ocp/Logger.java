package com.coderising.ood.ocp;

public class Logger {
    private Formatter formatter;
    private Sender sender;

    public Logger(Formatter formatter, Sender sender) {
	this.formatter = formatter;
	this.sender = sender;
    }

    public void log(String msg) {
	sender.send(formatter.formate(msg));
    }
}
