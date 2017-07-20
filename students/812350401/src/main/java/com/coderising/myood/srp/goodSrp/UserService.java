package com.coderising.myood.srp.goodSrp;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by thomas_young on 24/6/2017.
 */
public class UserService {

    public List<User> getUsers(List<Product> ps) {
        List<User> users = new LinkedList<>();
        String sql = "Select name from subscriptions where send_mail=1";
        System.out.println("loadQuery set\n");
        List userInfoList = DBUtil.query(sql);
        for (Object userInfo: userInfoList) {
            User user = new User();
            user.setName(((Map<String, String>)userInfo).get("NAME"));
            user.setEmailAddress(((Map<String, String>)userInfo).get("EMAIL"));
            user.setSubscribedProducts(ps);
            users.add(user);
        }
        return users;
    }
}
