package com.coderising.ood.srp;

import java.util.List;

/**
 * @author nvarchar
 *         date 2017/6/29
 */
public class User {
    private String name;
    private String emailAddress;

    private List<Product> products;

    public String getName() {
        return name;
    }

    public String getEMailAddress() {
        return emailAddress;
    }

    public List<Product> getSubscribedProducts() {
        return this.products;
    }

}
