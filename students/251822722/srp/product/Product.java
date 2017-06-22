package com.coderising.ood.srp.product;

import com.coderising.ood.srp.DBUtil;
import com.coderising.ood.srp.user.User;

import java.util.List;

/**
 * com.coderising.ood.srp.product
 * Created by Eric Wang on 6/19/17.
 */
public class Product {
    String productDesc = null;

    String productID = null;


    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }


    public void notifyUserPriceChange() {


        List<User> userList = DBUtil.query(setLoadQuery(this.productID));

        userList.stream().forEach(user -> {
            user.sendPriceChangeEmail(this);
        });

    }

    private String setLoadQuery(String productID) {

        System.out.println("loadQuery set");

        return "Select name from subscriptions "
                + "where product_id= '" + productID + "' "
                + "and send_mail=1 ";


    }

}
