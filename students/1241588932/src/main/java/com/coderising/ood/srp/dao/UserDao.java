package com.coderising.ood.srp.dao;

import com.coderising.ood.srp.entity.User;
import com.coderising.ood.srp.util.DBUtil;

import java.util.List;

/**
 * Created by Enan on 17/6/14.
 */
public class UserDao {

    private static UserDao INSTANCE;

    public static UserDao getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDao.class) {
                INSTANCE = new UserDao();
            }
        }
        return INSTANCE;
    }

    private UserDao() {}

    public List<User> querySubscriptedUsersByProductID(String productID) {
        String sql = "Select name, email from subscriptions "
                + "where product_id= '" + productID +"' "
                + "and send_mail=1 ";
        return DBUtil.query(sql);
    }
}
