package com.coderising.ood.srp;

/**
 * Created by Dell on 2017/6/15.
 */
public class Product {

    private String productID = null;
    private String productDesc = null;



    public void setProductID(String productID)
    {
        this.productID = productID;

    }

    public String getproductID()
    {
        return productID;
    }


    public void setProductDesc(String desc) {
        this.productDesc = desc;
    }

    public String getProductDesc() {
        return productDesc;
    }
}
