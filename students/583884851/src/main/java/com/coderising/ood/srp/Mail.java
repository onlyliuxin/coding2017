package com.coderising.ood.srp;

/**
 * 邮件类
 *
 * @author chengyu
 * @version 17/6/20
 */
public class Mail {
    protected String smtpHost = null;
    protected String altSmtpHost = null;
    protected String fromAddress = null;
    protected String toAddress = null;
    protected String subject = null;
    protected String message = null;

    public Mail() {

    }

    public Mail(Configuration config) {
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    public void send() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(toAddress).append("\n");
        buffer.append("Subject:").append(subject).append("\n");
        buffer.append("Content:").append(message).append("\n");
        System.out.println(buffer.toString());
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public void setContent(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }
}
