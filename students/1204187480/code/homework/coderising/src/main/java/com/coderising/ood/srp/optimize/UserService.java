package com.coderising.ood.srp.optimize;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户service, 管理用户的数据
 * Created by luoziyihao on 6/12/17.
 */
public class UserService {

    List<User> loadMailingList() {
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            User user = new User();
            user.setName("User" + i);
            user.setEmail("aa@bb.com");
            userList.add(user);
        }
        return userList;
    }
}
