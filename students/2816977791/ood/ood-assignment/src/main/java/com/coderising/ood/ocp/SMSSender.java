package com.coderising.ood.ocp;

/**
 * @author nvarchar
 *         date 2017/6/28
 */
public class SMSSender implements Sender {
    @Override
    public void send(String logMsg) {
        SMSUtil.send(logMsg);
    }
}
