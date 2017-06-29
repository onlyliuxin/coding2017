package com.thomsom.coderising.ood.srp;

/**
 * the email message entity class.
 *
 * @author Thomson Tang
 * @version Created: 23/06/2017.
 */
public class Email {
    private String fromAddress;
    private String toAddress;
    private String subject;
    private String content;

    public Email() {
    }

    public Email(String fromAddress, String toAddress, String subject, String content) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.subject = subject;
        this.content = content;
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
}
