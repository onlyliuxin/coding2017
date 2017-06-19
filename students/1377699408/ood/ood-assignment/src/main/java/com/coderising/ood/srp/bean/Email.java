package com.coderising.ood.srp.bean;


import com.coderising.ood.srp.utils.CollectionUtils;
import com.coderising.ood.srp.EmailException;
import com.coderising.ood.srp.utils.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Email {
    private String fromAddress;
    private List<String> toAddresses;
    private String subject;
    private String content;
    private String smtpServer;
    private String email_admin;

    public Email(String fromAddress, List<String> toAddresses, String subject, String content, String smtpServer, String email_admin) {
        this.fromAddress = fromAddress;
        this.toAddresses = toAddresses;
        this.subject = subject;
        this.content = content;
        this.smtpServer = smtpServer;
        this.email_admin = email_admin;
    }

    public void send() throws EmailException {
        //假装发了一封邮件
        check_send();
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(this.getFromAddress()).append("\n");
        buffer.append("To:").append(Arrays.toString(this.getToAddresses().toArray())).append("\n");
        buffer.append("Subject:").append(this.getSubject()).append("\n");
        buffer.append("Content:").append(this.getContent()).append("\n");
        System.out.println(buffer.toString());
    }

    private void check_send() throws EmailException {
        if (StringUtils.isBlank(this.fromAddress)) {
            throw new EmailException("fromAddress is empty");
        }
        if (CollectionUtils.isEmpty(this.toAddresses)) {
            throw new EmailException("toAddresses is empty");
        }
        if (StringUtils.isBlank(this.subject)) {
            throw new EmailException("subject is empty");
        }
    }

    public Email() {
    }


    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public List<String> getToAddresses() {
        return toAddresses;
    }

    public void setToAddresses(List<String> toAddresses) {
        this.toAddresses = toAddresses;
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

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getEmail_admin() {
        return email_admin;
    }

    public void setEmail_admin(String email_admin) {
        this.email_admin = email_admin;
    }
}
