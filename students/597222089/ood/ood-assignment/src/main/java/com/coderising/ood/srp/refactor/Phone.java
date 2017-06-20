package com.coderising.ood.srp.refactor;

/**
 * Created by walker on 2017/6/19.
 */

public class Phone {
    private String productID = null;
    private String productDesc = null;

    public Phone(String productID, String productDesc) {
        this.productID = productID;
        this.productDesc = productDesc;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getProductID() {
        return productID;
    }
}
