package com.coderising.mydp.decorator;

import com.coderising.mydp.utils.Encryptor;

/**
 * Created by thomas_young on 25/7/2017.
 */
public class EmailEncrypt extends EmailDecorator {
    private Email email;
    private String key;
    private String initVector;

    public EmailEncrypt(String key, String initVector, Email email) {
        this.key = key;
        this.initVector = initVector;
        this.email = email;
    }

    @Override
    public String getContent() {
        String content = email.getContent();
        return Encryptor.encrypt(key, initVector, content);
    }
}
