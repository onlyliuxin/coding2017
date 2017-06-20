package com.mimieye.odd.srp.dao;

import com.mimieye.odd.srp.util.DBUtil;

import java.util.List;

/**
 * Created by Pierreluo on 2017/6/15.
 */
public interface UserInfoDAO {

    List loadMailingList(String productID) throws Exception;
}
