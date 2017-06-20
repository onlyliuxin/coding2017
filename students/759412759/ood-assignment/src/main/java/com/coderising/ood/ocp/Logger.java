package com.coderising.ood.ocp;

public class Logger {

    private Printer printer;
    private Formater formater;


    public Logger(Printer printer, Formater formater) {
        this.printer = printer;
        this.formater = formater;
    }


    public void log(String msg) {
        String logMsg = formater.formatMessage(msg);
        printer.print(logMsg);
    }
}

