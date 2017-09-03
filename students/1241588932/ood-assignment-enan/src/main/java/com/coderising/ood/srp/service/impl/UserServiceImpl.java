package com.coderising.ood.srp.service.impl;

import com.coderising.ood.srp.dao.UserDao;
import com.coderising.ood.srp.entity.User;
import com.coderising.ood.srp.service.IUserService;

import java.util.List;

/**
 * Created by Enan on 17/6/18.
 */
public class UserServiceImpl implements IUserService {

    private UserDao userDao = UserDao.getInstance();

    @Override
    public List<User> querySubscriptedUsersByProductID(String productID) {
        return userDao.querySubscriptedUsersByProductID(productID);
    }
}
