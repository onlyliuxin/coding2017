package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mori on 2017/6/15.
 */
public class UserService {

    public static List<User> loadMailingListByProductId(String productID) {
        String sql = "Select name from subscriptions "
                + "where product_id= '" + productID + "' "
                + "and send_mail=1 ";
        return DBUtil.query(sql);
    }
}
