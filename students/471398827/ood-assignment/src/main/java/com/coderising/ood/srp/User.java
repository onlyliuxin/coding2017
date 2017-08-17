package com.coderising.ood.srp;

/**
 * Created by szf on 6/20/17.
 */
public class User {
    private String Name;

    public User(String name, String email) {
        Name = name;
        Email = email;
    }

    public String getName() {

        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    private String Email;
}
