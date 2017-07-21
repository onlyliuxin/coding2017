package com.coderising.ood.ocp.sender;

/**
 * Created by Iden on 2017/6/21.
 */
public class EmailSender implements Sender {
    @Override
    public void send(String msg) {
        System.out.println("Email发送，内容为："+msg);
    }
}
