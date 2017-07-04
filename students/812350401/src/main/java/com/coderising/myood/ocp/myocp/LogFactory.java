package com.coderising.myood.ocp.myocp;

/**
 * Created by thomas_young on 24/6/2017.
 */
public class LogFactory {
    public Logger createLogger(int logType, int logMethod) {
        SenderFactory senderFactory = new SenderFactory();
        FormatterFactory formatterFactory = new FormatterFactory();
        Formatter formatter = formatterFactory.createFormatter(logType);
        Sender sender = senderFactory.createSender(logMethod);
        return new Logger(formatter, sender);
    }
}
