package com.coderings.dp.decorator;

/**
 * @author nvarchar
 *         date 2017/7/26
 */
public class EncryptEmailImpl extends EmailDecorator {

    public EncryptEmailImpl(Email email) {
        super(email);
    }

    private String encrypt(String content) {
        return "****" + content + "****";
    }

    public String getContent() {
        return encrypt(email.getContent());
    }
}
