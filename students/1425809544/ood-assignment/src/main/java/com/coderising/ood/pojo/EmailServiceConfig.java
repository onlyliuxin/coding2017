package com.coderising.ood.pojo;

/**
 * @author xyy
 * @create 2017-06-19 10:00
 **/
public class EmailServiceConfig {

    private String smtpHost;
    private String altSmtpHost;
    private String fromAddress;

    public EmailServiceConfig(String smtpHost, String altSmtpHost, String fromAddress) {
        this.smtpHost = smtpHost;
        this.altSmtpHost = altSmtpHost;
        this.fromAddress = fromAddress;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public EmailServiceConfig setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
        return this;
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }

    public EmailServiceConfig setAltSmtpHost(String altSmtpHost) {
        this.altSmtpHost = altSmtpHost;
        return this;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public EmailServiceConfig setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
        return this;
    }
}
