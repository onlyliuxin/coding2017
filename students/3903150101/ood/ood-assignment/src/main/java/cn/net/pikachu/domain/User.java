package cn.net.pikachu.domain;

import java.util.*;

/**
 * 用户
 */
public class User {

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * 用户名
     */
    private String username;

    /**
     * 电子邮箱
     */
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}