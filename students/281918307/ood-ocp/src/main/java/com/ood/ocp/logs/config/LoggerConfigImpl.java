package com.ood.ocp.logs.config;

import com.ood.ocp.logs.content.ContentService;
import com.ood.ocp.logs.sender.LoggerSender;
import com.ood.ocp.logs.sender.LoggerSenderWacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ajaxfeng on 2017/6/24.
 */
@Service
public class LoggerConfigImpl implements LoggerConfig {
    @Autowired
    private LoggerSenderWacher loggerSenderWacher;

    private int contentType;

    private List<Integer> sendTypeList;
    @Autowired
    private LoggerSender mailLoggerSender;
    @Autowired
    private LoggerSender smsLoggerSender;
    @Autowired
    private LoggerSender consoleLoggerSender;
    @Autowired
    private ContentService contentService;
    @Autowired
    private ContentService dateContentService;


    @Override
    public ContentService getContentService() {
        if (RAW_LOG == contentType) {
            return contentService;
        }
        if (RAW_LOG_WITH_DATE == contentType) {
            return dateContentService;
        }

        return contentService;
    }

    private void initLoggerWacher(){
        if(sendTypeList.contains(EMAIL_LOG)){
            loggerSenderWacher.addLoggerSender(mailLoggerSender);
        }
         if(sendTypeList.contains(SMS_LOG)){
            loggerSenderWacher.addLoggerSender(smsLoggerSender);
        }
         if(sendTypeList.contains(PRINT_LOG)){
            loggerSenderWacher.addLoggerSender(consoleLoggerSender);
        }
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public List<Integer> getSendTypeList() {
        return sendTypeList;
    }

    /**
     * 设置类型
     * @param sendTypeList
     */
    public void setSendTypeList(List<Integer> sendTypeList) {
        this.sendTypeList = sendTypeList;
        initLoggerWacher();
    }
}
