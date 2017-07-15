package com.ood.srp.user;

/**
 * 用户数据类。
 *
 * @since 06.18.2017
 */
public class UserInfo {
    private String name;
    private String email;
    private String productDesc;

    public UserInfo(String name, String email, String productDesc) {
        this.name = name;
        this.email = email;
        this.productDesc = productDesc;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProductDesc() {
        return productDesc;
    }
}
