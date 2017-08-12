package com.coderising.ood.ocp.mine;

/**
 * Created by guodongchow on 2017/6/21.
 */
public class SMSProcessor implements Processor {
    public void process(String message) {
        System.out.println("SMS sending message :" + message);
    }
}
