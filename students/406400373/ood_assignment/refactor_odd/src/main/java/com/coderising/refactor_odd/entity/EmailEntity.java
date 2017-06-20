package com.coderising.refactor_odd.entity;

/**
 * @author cenkailun
 * @Date 17/6/19
 * @Time 下午8:43
 */
public class EmailEntity {

    private String fromAddress;
    private String toAddress;
    private String subject;
    private String message;

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
}
