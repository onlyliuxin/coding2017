package ocp.logger;

import ocp.util.MailUtil;
import ocp.logType.LogType;

/**
 * ocp.ocp.logger
 * Created by Eric Wang on 6/21/17.
 */
public class MailLogger extends Logger {

    LogType logType;

    public MailLogger(LogType logType) {
        this.logType = logType;
    }


    public void log(String msg) {

        this.logType.setMessage(msg);
        MailUtil.send(logType.getMessage());
    }
}
