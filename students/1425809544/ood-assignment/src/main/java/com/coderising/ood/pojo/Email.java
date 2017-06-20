package com.coderising.ood.pojo;

/**
 * @author xyy
 * @create 2017-06-19 9:44
 **/
public class Email {


    private String toAddress;
    private String subject;
    private String message;

    public Email() {
    }

    public Email(String toAddress, String subject, String message) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.message = message;
    }

    public String getToAddress() {
        return toAddress;
    }

    public Email setToAddress(String toAddress) {
        this.toAddress = toAddress;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Email setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Email setMessage(String message) {
        this.message = message;
        return this;
    }
}
