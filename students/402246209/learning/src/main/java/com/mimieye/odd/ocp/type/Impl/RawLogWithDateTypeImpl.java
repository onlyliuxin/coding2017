package com.mimieye.odd.ocp.type.Impl;

import com.mimieye.odd.ocp.type.TypeInterface;
import com.mimieye.odd.ocp.util.DateUtil;

/**
 * Created by Pierreluo on 2017/6/20.
 */
public class RawLogWithDateTypeImpl implements TypeInterface {

    @Override
    public String getMsg(String msg) {
        String logMsg = msg;
        String txtDate = DateUtil.getCurrentDateAsString();
        logMsg = txtDate + ": " + msg;
        return logMsg;
    }
}
