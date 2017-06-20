package com.coderising.ood.srp;

import java.util.List;

/**
 * @author yangdd
 */
public class UserInfoService {

    public List<UserInfo> getuserInfoByProduceId(String productId) {
        return DBUtil.querySubscriber(productId);
    }
}
