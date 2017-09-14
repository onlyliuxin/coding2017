package com.coderings.dp.decorator;

public abstract class EmailDecorator implements Email{
    protected Email email;

    public EmailDecorator(Email email) {
        this.email = email;
    }

    public String getContent() {
        return email.getContent();
    }
}