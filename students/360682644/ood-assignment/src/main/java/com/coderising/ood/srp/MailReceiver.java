package com.coderising.ood.srp;

/**
 * Created by 360682644 on 2017/6/13.
 */
public class MailReceiver {
    private String name;
    private String email;
    private String message;
    private String subject;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(IMailable IMailable) {
        message = IMailable.toEmailText(name);
    }
    public void setSubject(IMailable IMailable) {
        subject = IMailable.toEmailSubject(null);
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getSubject() {
        return subject;
    }
}
