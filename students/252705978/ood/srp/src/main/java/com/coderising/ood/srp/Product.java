package com.coderising.ood.srp;

/**
 * 产品实体类
 * 
 * @author lin
 * @since
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

    public Product(String[] strs) {
        setProductID(strs[0]);
        setProductDesc(strs[1]);
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
