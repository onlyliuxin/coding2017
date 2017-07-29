package com.coderising.mydp.decorator;

/**
 * Created by thomas_young on 25/7/2017.
 */
public class EmailSendOut extends EmailDecorator {
    Email email;

    public EmailSendOut(Email email) {
        this.email = email;
    }

    @Override
    public String getContent() {
        String content = email.getContent();
        content += "\n" + "本邮件仅为个人观点，并不代表公司立场.";
        return content;
    }
}
