package com.coderising.ood.ocp.myocp;
// 有几个问题： Msg和Method起名不好，建议起Formatter和Sender
// 其次，可以建FormatterFactory和SenderFactory来分别建工厂

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
