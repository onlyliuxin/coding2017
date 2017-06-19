package com.mimieye.odd.srp.dao.impl;

import com.mimieye.odd.srp.dao.UserInfoDAO;
import com.mimieye.odd.srp.util.DBUtil;

import java.util.List;

/**
 * Created by Pierreluo on 2017/6/15.
 */
public class UserInfoDAOImpl implements UserInfoDAO {

    public List loadMailingList(String productID) throws Exception {
        String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + productID + "' "
                + "and send_mail=1 ";
        System.out.println("loadQuery set");
        return DBUtil.query(sendMailQuery);
    }
}
