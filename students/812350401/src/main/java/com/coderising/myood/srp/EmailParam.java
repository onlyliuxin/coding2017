package com.coderising.myood.srp;


/**
 * Created by thomas_young on 20/6/2017.
 */
public class EmailParam {
    private String smtpHost = null;
    private String altSmtpHost = null;
    private static Configuration config = new Configuration();
    private String fromAddress = null;

    protected void setFromAddress()
    {
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    private void loadEmailConfig() {
        setFromAddress();
        setSMTPHost();
        setAltSMTPHost();
    }

    public EmailParam() {
        loadEmailConfig();
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    private void setSMTPHost()
    {
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    private void setAltSMTPHost()
    {
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
    }
}
