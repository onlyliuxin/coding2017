package com.coderising.ood.ocp;

public class Logger {

    Sender sender;
    LogMsgConvert convert;

    public Logger(int logType, int logMethod) {
        convert = RawLogFactory.createFormatter(logType);
        sender = SenderFactory.createSenderFormat(logMethod);
    }

    public void log(String msg) {
        sender.send(convert.getLogFromMsg(msg));
    }

    public static void main(String[] args) {
        Logger logger = new Logger(1, 3);
        logger.log("123");
    }
}

