package com.coderising.ood.mytest;

/**
 * Created by Arthur on 2017/6/17.
 */
public class AltSMTPHost implements Host {

    @Override
    public String setHost() {
        return this.configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
    }

}
