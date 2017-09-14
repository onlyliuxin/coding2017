package com.coderising.dp.week2.decorator;

import java.nio.charset.Charset;
import java.util.Base64;

public class EmailDecorator implements Email {

    private Email email;

    private String rawContent;

    private String handledContent;

    private EmailType emailType;

    public EmailDecorator(Email email) {
        this(email, EmailType.PRIVATE);
    }

    public EmailDecorator(Email email, EmailType emailType) {
        this.email = email;
        handle(email.getContent(), emailType);
    }

    @Override
    public String getContent() {
        if (isModified()) {
            handle(this.email.getContent(), this.emailType);
        }
        return this.handledContent;
    }

    private void handle(String rawString, EmailType emailType) {
        this.rawContent = rawString;
        this.emailType = emailType;
        String decodeString = decode(rawString);
        this.handledContent = emailType == EmailType.PRIVATE ? decodeString : (decodeString + "本邮件仅为个人观点,并不代表公司立场");

    }

    private String decode(String rawString) {
        return new String(Base64.getEncoder().encode(rawString.getBytes()), Charset.defaultCharset());
    }

    private boolean isModified() {
        if (this.rawContent == null) {
            throw new RuntimeException();
        }
        return email != null && rawContent.equals(email.getContent());
    }
}
