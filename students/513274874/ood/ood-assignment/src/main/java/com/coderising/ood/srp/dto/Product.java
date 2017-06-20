package com.coderising.ood.srp.dto;

/**
 * Created by guodongchow on 2017/6/15.
 */
public class Product {

    String productID;
    String productDesc;

    public void setProductID(String productID)
    {
        this.productID = productID;

    }

    public void setProductDesc(String desc) {
        this.productDesc = desc;
    }


    public String getProductID() {
        return productID;
    }

    public String getProductDesc() {
        return productDesc;
    }
}
