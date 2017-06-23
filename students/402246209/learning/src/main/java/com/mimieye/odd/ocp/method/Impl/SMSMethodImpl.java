package com.mimieye.odd.ocp.method.Impl;

import com.mimieye.odd.ocp.method.MethodInterface;
import com.mimieye.odd.ocp.util.MailUtil;
import com.mimieye.odd.ocp.util.SMSUtil;

/**
 * Created by Pierreluo on 2017/6/20.
 */
public class SMSMethodImpl implements MethodInterface {

    @Override
    public void execute(String logMsg) {
        SMSUtil.send(logMsg);
    }
}
