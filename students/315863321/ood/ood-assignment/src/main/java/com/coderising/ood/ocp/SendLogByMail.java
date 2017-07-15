package com.coderising.ood.ocp;

/**
 * Created by john on 2017/6/20.
 */
public class SendLogByMail implements SendLog {
    @Override
    public void send(String msg) {
        MailUtil.send(msg);
    }
}
