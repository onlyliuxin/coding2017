package com.coderising.ood.srp;

import com.coderising.ood.srp.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {

    /**
     * 应该从数据库读， 但是简化为直接生成。
     *
     * @param sql
     * @return
     */
    public static List query(String sql) {

        List userList = new ArrayList();
        for (int i = 1; i <= 3; i++) {
            HashMap userInfo = new HashMap();
            userInfo.put("NAME", "user" + i);
            userInfo.put("EMAIL", "aa@bb.com");
            userList.add(userInfo);
        }

        return userList;
    }


    public static Product queryProduct(
            String productDesc,

            String productID) {

        //TODO

        return new Product();
    }


}
