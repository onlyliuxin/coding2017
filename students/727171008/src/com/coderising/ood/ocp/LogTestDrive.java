package com.coderising.ood.ocp;

public class LogTestDrive {

    public static void main(String[] args) {
	FormatterFactory ff = new FormatterFactory();
	SenderFactory sf = new SenderFactory();
	
	Formatter formatter = ff.createFormatter(1);
	Sender sender = sf.createSender(1);
	
	Logger logger = new Logger(formatter, sender);
	String msg = "此处应该从文本读取？或者html读取？";
	logger.log(msg);
	
	System.out.println("end");

    }

}
