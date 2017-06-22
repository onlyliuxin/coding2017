package ocp.logger;

import ocp.logType.LogType;

/**
 * ocp.ocp.logger
 * Created by Eric Wang on 6/21/17.
 */
public class DateLogger extends Logger {

    LogType logType;

    public DateLogger(LogType logType) {
        this.logType = logType;
    }


    public void log(String msg) {

        this.logType.setMessage(msg);
        System.out.println(logType.getMessage());
    }
}
