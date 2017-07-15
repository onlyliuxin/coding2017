package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ConsoleUtil consoleUtil = new ConsoleUtil();
        Formatter formatter = new Formatter();
        Logger logger = new Logger(formatter,consoleUtil);
        logger.log("abc");
        MailUtil mailUtil = new MailUtil();
        DateFormatter dateformatter = new DateFormatter();
        Logger logger2 = new Logger(dateformatter,mailUtil);
        logger2.log("efg");

    }
}
