package com.ood.ocp.logs.sender;

import org.springframework.stereotype.Service;

/**
 * Created by ajaxfeng on 2017/6/25.
 */
@Service("consoleLoggerSender")
public class ConsoleLoggerSender implements LoggerSender {
    @Override
    public String sendLog(String logMsg) {
        System.out.println(logMsg);
        return null;
    }
}
