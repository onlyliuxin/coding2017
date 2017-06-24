package com.ood.ocp.logs.sender;

import com.ood.ocp.util.SMSUtil;
import org.springframework.stereotype.Service;

/**
 * 短信
 * Created by ajaxfeng on 2017/6/24.
 */
@Service("smsLoggerSender")
public class SMSLoggerSender implements LoggerSender {
    @Override
    public String sendLog(String logMsg) {
        SMSUtil.send(logMsg);
        return null;
    }
}
