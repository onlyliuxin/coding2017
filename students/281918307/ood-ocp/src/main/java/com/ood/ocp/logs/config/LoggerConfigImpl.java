package com.ood.ocp.logs.config;

import com.ood.ocp.logs.content.ContentService;
import com.ood.ocp.logs.sender.LoggerSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ajaxfeng on 2017/6/24.
 */
@Service
public class LoggerConfigImpl implements LoggerConfig {

    private int contentType;

    private int sendType;
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

    @Override
    public LoggerSender getLoggerSender() {
        if (EMAIL_LOG == sendType) {
            return mailLoggerSender;
        }
        if (SMS_LOG == sendType) {
            return smsLoggerSender;
        }
        if (PRINT_LOG == sendType) {
            return consoleLoggerSender;
        }
        return mailLoggerSender;
    }


    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }
}
