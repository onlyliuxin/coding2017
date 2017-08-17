package com.coderising.ood.srp.refactor;

/**
 * Created by walker on 2017/6/20.
 */

public class Consumer {
    private String name;
    private String email;

    public Consumer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
