package com.coderising.ood.ocp;

/**
 * @author nvarchar
 *         date 2017/6/28
 */
public class RawLogWtihDate implements LogMsgConvert {

    @Override
    public String getLogFromMsg(String msg) {
        String txtDate = DateUtil.getCurrentDateAsString();
        String logMsg = txtDate + ": " + msg;
        return logMsg;
    }
}
