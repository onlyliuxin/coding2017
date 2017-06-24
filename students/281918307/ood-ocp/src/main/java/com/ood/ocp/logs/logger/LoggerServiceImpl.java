package com.ood.ocp.logs.logger;

import com.ood.ocp.logs.config.LoggerConfig;
import com.ood.ocp.logs.content.ContentService;
import com.ood.ocp.logs.sender.LoggerSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ajaxfeng on 2017/6/24.
 */
@Service
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    LoggerConfig loggerConfig;


    @Override
    public String logger(String logMsg) {

        loggerConfig.setContentType(1);
        loggerConfig.setSendType(1);


        ContentService contentService = loggerConfig.getContentService();
        String conteng = contentService.getConteng(logMsg);


        LoggerSender loggerSender = loggerConfig.getLoggerSender();
        loggerSender.sendLog(conteng);

        return "success";
    }
}
