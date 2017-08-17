package com.coderising.myood.srp;

import java.util.List;

/**
 * Created by thomas_young on 21/6/2017.
 */
public class UserDao {
    private String sendMailQuery = null;

    private void setLoadQuery(String productID) throws Exception {

        sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + productID +"' "
                + "and send_mail=1 ";


        System.out.println("loadQuery set");
    }

    public List loadMailingList(String productID) throws Exception {
        setLoadQuery(productID);
        return DBUtil.query(this.sendMailQuery);
    }

}
