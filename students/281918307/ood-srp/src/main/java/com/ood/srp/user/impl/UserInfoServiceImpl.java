package com.ood.srp.user.impl;

import com.ood.srp.user.UserInfo;
import com.ood.srp.user.UserInfoService;
import com.ood.srp.util.DBUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuxia on 2017/6/21.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public List<UserInfo> listUserInfo(String productID) {
        List<UserInfo> userInfoList = DBUtil.query(productID);
        return userInfoList;
    }

    @Override
    public List<UserInfo> listUserInfo(List<String> productIDList) {
        List<UserInfo> userInfoAll = new ArrayList<>();
        for (String id : productIDList) {
            List<UserInfo> userInfoList = listUserInfo(id);
            userInfoAll.addAll(userInfoList);
        }
        return userInfoAll;
    }
}
