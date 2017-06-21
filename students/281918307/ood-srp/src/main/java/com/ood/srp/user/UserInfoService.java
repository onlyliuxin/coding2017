package com.ood.srp.user;

import java.util.List;

/**
 * 用户逻辑
 * Created by ajaxfeng on 2017/6/20.
 */
public interface UserInfoService {
    /**
     * 获取用户信息
     *
     * @param productID
     * @return
     */
    public List<UserInfo> listUserInfo(String productID);

    /**
     * 获取用户信息
     *
     * @param productID
     * @return
     */
    public List<UserInfo> listUserInfo(List<String> productID);
}
