package com.coderising.ood.srp;

/**
 * the email message content will be sent to user.
 *
 * @author Thomson Tang
 * @version Created: 23/06/2017.
 */
public class EmailMessage {
    private String subject;
    private String message;

    public EmailMessage() {
    }

    public EmailMessage(String subject, String message) {
        this.subject = subject;
        this.message = message;
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
