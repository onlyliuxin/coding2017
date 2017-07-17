package com.ood.ocp.logs.sender;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志观察者
 * Created by ajaxfeng on 2017/6/26.
 */
@Service
public class LoggerSenderWacher {
    private List<LoggerSender> loggerSenders;

    public List<LoggerSender> getLoggerSenders() {
        return loggerSenders;
    }

    public void addLoggerSender(LoggerSender loggerSender){
         if(!loggerSenders.contains(loggerSender)){
             loggerSenders.add(loggerSender);
         }
    }

    public void setLoggerSenders(List<LoggerSender> loggerSenders) {
        this.loggerSenders = loggerSenders;
    }

    /**
     * 调用观察者发送消息
     * @param content
     */
    public void watch(String content){
        for(LoggerSender loggerSender:loggerSenders){
            loggerSender.sendLog(content);
        }
    }

}
