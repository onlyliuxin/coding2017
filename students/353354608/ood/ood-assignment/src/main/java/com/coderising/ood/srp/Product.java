package com.coderising.ood.srp;

/**
 * Created by zhenli on 17/6/11.
 */
public class Product {
    protected String productID = null;
    protected String productDesc = null;

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



    public void productIntruduce(){
        System.out.println("产品ID = " + productID + "\n");
        System.out.println("产品描述 = " + productDesc + "\n");
    }
}
