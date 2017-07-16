package com.coderising.ood.ocp.logtype;

/**
 * Created by wang on 2017/6/19.
 */
public class SmsLogTypeImp implements LogType {
    @Override
    public void Send(String msglog) {
        System.out.println(msglog);
    }
}
