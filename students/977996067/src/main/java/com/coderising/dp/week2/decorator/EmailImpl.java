package com.coderising.dp.week2.decorator;

public class EmailImpl implements Email {

    private String content;

    public EmailImpl(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return this.content;
    }
}
