package com.coderising.ood.srp;

import java.util.List;

/**
 * Created by john on 2017/6/14.
 */
public class BuildMailServer extends Build{
    private MailServer mailServer;

    public BuildMailServer(MailServer mailServer) {
        this.mailServer = mailServer;
    }
    void build() {
        List data = reader.read();
        mailServer.setSmtpHost((String) data.get(0));
        mailServer.setAltSmtpHost((String) data.get(1));
    }

}
