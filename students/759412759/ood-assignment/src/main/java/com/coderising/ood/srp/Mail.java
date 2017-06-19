package com.coderising.ood.srp;


public class Mail {

    private String smtpHost;
    private String altSmtpHost;
    private String fromAddress;

    private String toAddress;
    private String subject;
    private String message;
    private boolean debug;


    public Mail() {

    }

    public void init() {
        smtpHost = Configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
        altSmtpHost = Configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
        fromAddress = Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }

    public void setAltSmtpHost(String altSmtpHost) {
        this.altSmtpHost = altSmtpHost;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
