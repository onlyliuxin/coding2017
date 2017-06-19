package com.coderising.ood.srp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailDAO {
    public List<Map<String, String>> listSubscriptionsByProdoctId(String productId) {
        String sql = "Select name from subscriptions "
                + "where product_id= '" + productId + "' "
                + "and send_mail=1 ";
        List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
        for (int i = 1; i <= 3; i++) {
            Map<String, String> userInfo = new HashMap<String, String>();
            userInfo.put("NAME", "User" + i);
            userInfo.put("EMAIL", "aa@bb.com");
            userList.add(userInfo);
        }
        return userList;
    }
}
