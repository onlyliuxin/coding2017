package com.coderising.ood.srp;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
            userInfo.put("NAME", "User" + i);
            userInfo.put("EMAIL", "aa@bb.com");
            userList.add(userInfo);
        }

        return userList;
    }


    public static List<UserInfo> querySubscriber(String productId) {

        List<UserInfo> list = new ArrayList<>();

        Random random = new Random();
        int number = random.nextInt(3);

        for (int i = 1; i <= number; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName("User" + productId + i);
            userInfo.setMail(userInfo.getName() + "@" + productId + ".com");
            list.add(userInfo);
        }
        return list;
    }
}