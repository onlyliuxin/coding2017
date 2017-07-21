package com.ood.ocp.logs.sender;

import com.ood.ocp.util.MailUtil;
import org.springframework.stereotype.Service;

/**
 * 邮件实现类
 * Created by ajaxfeng on 2017/6/24.
 */
@Service("mailLoggerSender")
public class MailLoggerSender implements LoggerSender {
    @Override
    public String sendLog(String logMsg) {
        MailUtil.send(logMsg);
        return "success";
    }
}
