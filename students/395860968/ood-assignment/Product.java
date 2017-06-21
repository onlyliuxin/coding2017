package com.coderising.ood.srp;

/**
 * Created by kenhuang on 2017/6/15.
 */
public class Product {
    protected String productID = null;
    protected String productDesc = null;
   // protected String sendMailQuery = null;
    public Product(String productID, String productDesc) {
        this.productID = productID;
        this.productDesc = productDesc;
    }
    protected String generateLoadQuery() {
        System.out.println("loadQuery set");
        return  "Select name from subscriptions "
                + "where product_id= '" + productID +"' "
                + "and send_mail=1 ";

    }
}
