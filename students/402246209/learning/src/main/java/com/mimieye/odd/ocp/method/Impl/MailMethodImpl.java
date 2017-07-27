package com.mimieye.odd.ocp.method.Impl;

import com.mimieye.odd.ocp.method.MethodInterface;
import com.mimieye.odd.ocp.util.MailUtil;

/**
 * Created by Pierreluo on 2017/6/20.
 */
public class MailMethodImpl implements MethodInterface {
    @Override
    public void execute(String logMsg) {
        MailUtil.send(logMsg);
    }
}
