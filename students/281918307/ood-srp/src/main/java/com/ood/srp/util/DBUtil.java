package com.ood.srp.util;


import com.ood.srp.user.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类。
 * 管理数据库连接，查询等操作。
 *
 * @since 06.19.2017
 */
public class DBUtil {

    //TODO 此处添加数据库连接信息


    /**
     * 应该从数据库读， 但是简化为直接生成。
     * 给一个产品详情，返回一个Array List记载所有订阅该产品的用户信息（名字，邮箱，订阅的产品名称）。
     *
     * @param productID 传产品详情。产品id用来查询数据库。产品名称用于和用户信息绑定
     * @return 返回数据库中所有的查询到的结果。
     */
    public static List<UserInfo> query(String productID) {
        if (productID == null)
            return new ArrayList<>();

        String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + productID + "' "
                + "and send_mail=1 ";
        //假装用sendMilQuery查了数据库，生成了userList作为查询结果
        List<UserInfo> userList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            UserInfo newInfo = new UserInfo("User" + i, "aa@bb.com", "");
            userList.add(newInfo);
        }
        return userList;
    }
}
