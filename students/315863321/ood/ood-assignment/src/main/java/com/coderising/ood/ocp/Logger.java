package com.coderising.ood.ocp;

/**
 * Created by john on 2017/6/20.
 */
public class Logger {
    private FormatLog formatLog;
    private SendLog sendLog;

    public Logger(FormatLog f, SendLog s) {
        this.formatLog = f;
        this.sendLog = s;
    }

    public void log(String msg) {

        String logMsg = formatLog.format(msg);
        sendLog.send(logMsg);

    }
}
