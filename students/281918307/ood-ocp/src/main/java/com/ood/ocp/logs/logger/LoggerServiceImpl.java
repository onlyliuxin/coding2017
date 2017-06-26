package com.ood.ocp.logs.logger;

import com.ood.ocp.logs.config.LoggerConfig;
import com.ood.ocp.logs.content.ContentService;
import com.ood.ocp.logs.sender.LoggerSender;
import com.ood.ocp.logs.sender.LoggerSenderWacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajaxfeng on 2017/6/24.
 */
@Service
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    LoggerConfig loggerConfig;
    @Autowired
    LoggerSenderWacher loggerSenderWacher;


    @Override
    public String logger(String logMsg) {

        List<Integer> typeList=new ArrayList();
        typeList.add(1);
        typeList.add(2);
        typeList.add(3);

        loggerConfig.setSendTypeList(typeList);

        //构造内容
        ContentService contentService = loggerConfig.getContentService();
        String content = contentService.getConteng(logMsg);

        //推送消息
        loggerSenderWacher.watch(content);
        return "success";
    }
}
