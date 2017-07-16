package com.coderising.ood.srp;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Tudou on 2017/6/16.
 */
public class UserService {

    public List<HashMap<String, String>> loadMailingList(String productId) throws Exception {
        String sql = "Select name from subscriptions "
                + "where product_id= '" + productId + "' "
                + "and send_mail=1 ";
        return DBUtil.query(sql);
    }

}
