package com.coderising.ood.mytest;


/**
 * Created by Arthur on 2017/6/17.
 */
public class SMTPHost implements Host {

    @Override
    public String setHost() {
        return this.configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
    }

}
