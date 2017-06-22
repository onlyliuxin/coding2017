package com.coderising.ood.srp;

/**
 * Created by James on 6/15/2017.
 */
public class Mail {

    private Configuration config;

    private String smtpHost = null;
    private String altSmtpHost = null;
    private String fromAddress = null;

    public Mail(Configuration config) {
        this.config =config;
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);

    }

    protected void setSMTPHost() {
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
    }


    protected void setAltSMTPHost() {
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);

    }

    protected void setFromAddress() {
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    public void sendEmail(String toAddress, String subject, String message, boolean debug) throws Exception {
        MailUtil.sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);


    }

    public void sendAltEmail(String toAddress, String subject, String message, boolean debug) throws Exception{
        MailUtil.sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug);
    }
}
