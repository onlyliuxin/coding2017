package com.coderising.ood.srp.dto;

/**
 * Created by guodongchow on 2017/6/15.
 */
public class User {
    String name;
    String mailAddress;

    public User(String name, String mailAddress) {
        this.name = name;
        this.mailAddress = mailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
