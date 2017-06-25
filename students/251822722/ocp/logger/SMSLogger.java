package ocp.logger;

import ocp.util.SMSUtil;
import ocp.logType.LogType;

/**
 * ocp.ocp.logger
 * Created by Eric Wang on 6/21/17.
 */
public class SMSLogger extends Logger {

    LogType logType;

    public SMSLogger(LogType logType) {
        this.logType = logType;
    }


    public void log(String msg) {

        this.logType.setMessage(msg);
        SMSUtil.send(logType.getMessage());
    }
}
