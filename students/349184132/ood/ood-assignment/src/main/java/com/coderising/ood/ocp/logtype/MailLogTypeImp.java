package com.coderising.ood.ocp.logtype;

import com.coderising.ood.ocp.util.MailUtil;

/**
 * Created by wang on 2017/6/19.
 */
public class MailLogTypeImp implements LogType {
    @Override
    public void Send(String msglog) {
        MailUtil.send(msglog);
    }
}
