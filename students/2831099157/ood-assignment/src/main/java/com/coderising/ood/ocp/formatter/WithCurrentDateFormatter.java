package com.coderising.ood.ocp.formatter;

import com.coderising.ood.ocp.utils.DateUtil;

/**
 * Created by Iden on 2017/6/21.
 */
public class WithCurrentDateFormatter implements Formatter{

    @Override
    public String format(String msg) {
        String txtDate = DateUtil.getCurrentDateAsString();
        String logMsg = txtDate + ": " + msg;
        return logMsg;
    }
}
