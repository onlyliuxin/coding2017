package com.coderising.ood.ocp.myocp;


/**
 * Created by thomas_young on 24/6/2017.
 */
public class SmsMethod implements Method {
    @Override
    public void action(String msg) {
        SMSUtil.send(msg);
    }
}
