package com.coderising.ood.ocp;

/**
 * Created by john on 2017/6/20.
 */
public class SendLogBySMS implements SendLog {
    @Override
    public void send(String msg) {
        SMSUtil.send(msg);
    }
}
