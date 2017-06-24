package com.ood.ocp.logs.config;

import com.ood.ocp.logs.content.ContentService;
import com.ood.ocp.logs.sender.LoggerSender;

/**
 * Created by ajaxfeng on 2017/6/24.
 */
public interface LoggerConfig {

    public final int RAW_LOG = 1;
    public final int RAW_LOG_WITH_DATE = 2;
    public final int EMAIL_LOG = 1;
    public final int SMS_LOG = 2;
    public final int PRINT_LOG = 3;

    public ContentService getContentService();

    public LoggerSender getLoggerSender();

    public void setContentType(int contentType);

    public void setSendType(int sendType);

}
