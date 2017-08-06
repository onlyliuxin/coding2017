package com.coderising.ood.ocp.mine;

/**
 * Created by guodongchow on 2017/6/21.
 */
public class MailProcessor implements Processor {
    public void process(String message) {
        System.out.println("Mail sending message :"+message);
    }
}
