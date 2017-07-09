package com.coderising.ood.ocp.newb;

import com.coderising.ood.ocp.log.Log;
import com.coderising.ood.ocp.logtype.LogType;

/**
 * Created by wang on 2017/6/19.
 */
public class Logger {

    private Log log ;

    private LogType logType;

    public Logger(Log log, LogType logType) {
        this.log = log;
        this.logType = logType;
    }

    public void log(String msg){

        String msglog = log.setMsgLog(msg);

        logType.Send(msglog);

    }
}
