package com.mimieye.odd.srp.service.impl;

import com.mimieye.odd.srp.dao.UserInfoDAO;
import com.mimieye.odd.srp.service.UserInfoService;
import com.mimieye.odd.srp.util.DBUtil;

import java.util.List;

/**
 * Created by Pierreluo on 2017/6/15.
 */
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoDAO dao;

    public UserInfoServiceImpl(){}
    public UserInfoServiceImpl(UserInfoDAO dao){
        this.dao = dao;
    }

    public List loadMailingList(String productID) throws Exception {
        return dao.loadMailingList(productID);
    }
}
