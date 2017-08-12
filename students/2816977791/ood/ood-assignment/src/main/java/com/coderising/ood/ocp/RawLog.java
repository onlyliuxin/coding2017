package com.coderising.ood.ocp;

/**
 * @author nvarchar
 *         date 2017/6/28
 */
public class RawLog implements LogMsgConvert{

    @Override
    public String getLogFromMsg(String msg) {
        String logMsg = msg;
        return logMsg;
    }
}
