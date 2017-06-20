package com.coderising.ood.srp;

/**
 * 邮件数据
 *
 * @author yangdd
 */
public class MailData {

    protected String smtpHost;
    protected String altSmtpHost;
    protected String fromAddress;
    protected String toAddress;
    protected String subject;
    protected String message;
    private boolean debug;

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

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
