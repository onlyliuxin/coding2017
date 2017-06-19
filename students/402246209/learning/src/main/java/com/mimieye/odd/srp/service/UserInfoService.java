package com.mimieye.odd.srp.service;

import com.mimieye.odd.srp.util.DBUtil;

import java.util.List;

/**
 * Created by Pierreluo on 2017/6/15.
 */
public interface UserInfoService {
    List loadMailingList(String productID) throws Exception;
}
