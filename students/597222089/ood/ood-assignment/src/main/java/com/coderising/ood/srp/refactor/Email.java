package com.coderising.ood.srp.refactor;

/**
 * Created by walker on 2017/6/20.
 */

public class Email {
    private String subject;
    private String message;

    private String fromAddress = null;
    private String toAddress = null;

    public Email(String subject, String message, String fromAddress, String toAddress) {
        this.subject = subject;
        this.message = message;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }
}
