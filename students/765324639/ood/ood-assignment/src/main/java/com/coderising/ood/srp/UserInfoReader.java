package com.coderising.ood.srp;

import java.util.List;

public class UserInfoReader {

    public static List<UserInfo> readUserInfo(String productID) {
        String sendMailQuery = "Select name from subscriptions "
            + "where product_id= '" + productID +"' "
            + "and send_mail=1 ";
        System.out.println("loadQuery set");
        return DBUtil.query(sendMailQuery);
    }
}
