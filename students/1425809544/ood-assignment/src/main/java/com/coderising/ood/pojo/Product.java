package com.coderising.ood.pojo;

/**
 * 产品类
 *
 * @author xyy
 * @create 2017-06-19 9:30
 **/
public class Product {


    private String productID;
    private String productDesc;






    public String getProductID() {
        return productID;
    }

    public Product setProductID(String productID) {
        this.productID = productID;
        return this;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public Product setProductDesc(String productDesc) {
        this.productDesc = productDesc;
        return this;
    }
}
