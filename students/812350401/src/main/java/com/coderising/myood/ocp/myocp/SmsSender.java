package com.coderising.myood.ocp.myocp;


/**
 * Created by thomas_young on 24/6/2017.
 */
public class SmsSender implements Sender {
    @Override
    public void send(String msg) {
        SMSUtil.send(msg);
    }
}
