package com.coderising.ood.srp;

/**
 * Created by szf on 6/20/17.
 */
public class Mail {
    private String host;

    public Mail(String host) {
        this.host = host;
    }

    public boolean send(Message msg) throws Exception {
        System.out.println("Host: " + this.host);
        msg.print();
        return true;
    }
}
