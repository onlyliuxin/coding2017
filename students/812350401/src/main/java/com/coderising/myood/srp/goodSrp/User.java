package com.coderising.myood.srp.goodSrp;

import java.util.List;

/**
 * Created by thomas_young on 24/6/2017.
 */
public class User {
    private String name;
    private String emailAddress;

    private List<Product> subscribedProducts;

    public String getName(){
        return name;
    }
    public String getEMailAddress() {
        return emailAddress;
    }
    public List<Product> getSubscribedProducts(){
        return this.subscribedProducts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setSubscribedProducts(List<Product> subscribedProducts) {
        this.subscribedProducts = subscribedProducts;
    }
}
