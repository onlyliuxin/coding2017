package com.coderising.ood.srp.optimize;

/**
 * 邮件服务器配置类
 * Created by luoziyihao on 6/12/17.
 */
public class SmptPropeties {
    private String smtpHost =  "smtp.server";;
    private String altSmtpHost = "smtp1.163.com";
    private String fromAddress = " admin@company.com";

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
}
