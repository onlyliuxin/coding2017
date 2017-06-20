package com.coderising.ood.srp;

/**
 * Created by lenovo on 2017/6/13.
 */
public class Product {
    private String productID;
    private String productDesc;

    public Product(String productID, String productDesc) {
        this.productID = productID;
        this.productDesc = productDesc;
    }

    public String getproductID() {
        return productID;
    }

    public void setproductID(String productID) {
        this.productID = productID;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
