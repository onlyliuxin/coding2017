package com.coderising.ood.srp.model;

import com.coderising.ood.srp.configure.Configuration;
import com.coderising.ood.srp.configure.ConfigurationKeys;

/**
 * Created by Iden on 2017/6/14.
 */
public class Mail {
    private String fromAddress;
    private String toAddress;
    private String subject;
    private String content;
    private String smtpHost = null;
    private String altSmtpHost = null;

    public Mail() {
        Configuration config = new Configuration();
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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


    public void send() {
        if(null==toAddress){
            System.out.println("发送地址不能为空");
            return;
        }
        try {
            sendMailBySmtpHost();
        } catch (Exception e) {
            try {
                sendMailBySmtpHost();
            } catch (Exception e2) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
            }

        }
    }

    private void sendMailBySmtpHost() {
        System.out.println("通过SMTP服务器开始发送邮件");
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(toAddress).append("\n");
        buffer.append("Subject:").append(subject).append("\n");
        buffer.append("Content:").append(content).append("\n");
        System.out.println(buffer.toString());
    }

    private void sendMailByAlSmtpHost() {
        System.out.println("通过备用SMTP服务器开始发送邮件");
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(toAddress).append("\n");
        buffer.append("Subject:").append(subject).append("\n");
        buffer.append("Content:").append(content).append("\n");
        System.out.println(buffer.toString());
    }
}
