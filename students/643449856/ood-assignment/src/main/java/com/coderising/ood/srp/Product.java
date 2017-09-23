package com.coderising.ood.srp;

/**
 * Created by nengneng on 2017/6/19.
 */
public class Product {

    private String productID;
    private String productDesc;

    public Product(String productID, String productDesc) {
        this.productID = productID;
        this.productDesc = productDesc;
    }

    public Product() {
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
