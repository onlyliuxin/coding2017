package com.coderising.ood.pojo;

/**
 * @author xyy
 * @create 2017-06-19 9:48
 **/
public class User {

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
}
