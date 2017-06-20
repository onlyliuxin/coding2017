package com.coderising.ood.ocp;

/**
 * Created by john on 2017/6/20.
 */
public class SendLogByPrint implements SendLog {
    @Override
    public void send(String msg) {
        System.out.println(msg);
    }
}
