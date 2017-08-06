package com.coderising.ood.srp;

/**
 * Created by szf on 6/20/17.
 */
public class Product {
    public String getProductId() {
        return productId;
    }

    public Product(String productId, String productDesc) {
        this.productId = productId;
        this.productDesc = productDesc;
    }

    public Product() {
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    private String productId;
    private String productDesc;
}
