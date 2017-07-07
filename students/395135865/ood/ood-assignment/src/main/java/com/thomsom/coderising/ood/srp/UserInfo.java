package com.thomsom.coderising.ood.srp;

import java.util.ArrayList;
import java.util.List;

/**
 * the user info entity class.
 *
 * @author Thomson Tang
 * @version Created: 23/06/2017.
 */
public class UserInfo {
    private String userId;
    private String userName;
    private String email;

    List<Product> products = new ArrayList<>();

    public UserInfo() {
    }

    public UserInfo(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
