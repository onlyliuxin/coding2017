package com.coderising.ood.srp;

/**
 * Created by Mori on 2017/6/15.
 */
public class Product {

    private String productID;
    private String productDesc;

    public Product() {
    }

    public Product(String productID, String productDesc) {
        this.productID = productID;
        this.productDesc = productDesc;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
