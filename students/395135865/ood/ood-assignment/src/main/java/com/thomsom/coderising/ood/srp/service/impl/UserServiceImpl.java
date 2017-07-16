package com.thomsom.coderising.ood.srp.service.impl;

import com.thomsom.coderising.ood.srp.UserInfo;
import com.thomsom.coderising.ood.srp.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * the implementation of user service.
 *
 * @author Thomson Tang
 * @version Created: 29/06/2017.
 */
public class UserServiceImpl implements UserService {

    @Override
    public List<UserInfo> listUser() throws Exception {
        List<UserInfo> userInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UserInfo userInfo = new UserInfo(String.valueOf(i), "user" + i, String.format("user%d@qq.com", i));
            userInfos.add(userInfo);
        }
        return userInfos;
    }
}
