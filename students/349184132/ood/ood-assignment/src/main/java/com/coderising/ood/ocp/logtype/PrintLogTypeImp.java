package com.coderising.ood.ocp.logtype;

import com.coderising.ood.ocp.util.SMSUtil;

/**
 * Created by wang on 2017/6/19.
 */
public class PrintLogTypeImp implements LogType{
    @Override
    public void Send(String msglog) {
        SMSUtil.send(msglog);
    }
}
