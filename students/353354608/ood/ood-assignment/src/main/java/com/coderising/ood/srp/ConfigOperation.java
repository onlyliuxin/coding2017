package com.coderising.ood.srp;

/**
 * Created by zhenli on 17/6/11.
 */
public class ConfigOperation {

    protected String smtpHost = null;

    protected String altSmtpHost = null;

    protected String fromAddress = null;

    private Configuration config;

//    public void setConfig(Configuration config) {
//        this.config = config;
//    }


    public String getFromAddress() {
        return fromAddress;
    }

    public ConfigOperation(Configuration config) {
        this.config = config;
    }

    protected void setSMTPHost()
    {
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
    }

    protected void setAltSMTPHost()
    {
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);

    }

    protected void setFromAddress()
    {
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }
}
