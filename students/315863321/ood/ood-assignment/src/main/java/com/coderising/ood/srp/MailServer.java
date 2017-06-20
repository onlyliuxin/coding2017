package com.coderising.ood.srp;

/**
 * Created by john on 2017/6/14.
 */
public class MailServer {
    protected String smtpHost = null;
    protected String altSmtpHost = null;

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
}
