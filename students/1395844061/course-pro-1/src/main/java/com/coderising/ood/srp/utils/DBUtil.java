package com.coderising.ood.srp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DBUtil
 *
 * @author Chenpz
 * @package com.coderising.ood.srp
 * @date 2017/6/12/23:32
 */
public final class DBUtil {

    private DBUtil(){
        throw new RuntimeException("illegal called!");
    }

    /**
     * 应该从数据库读， 但是简化为直接生成。
     * @param sql
     * @return
     */
    public static List<Map<String, String>> query(String sql){
        System.out.println("sql: "+sql);
        List<Map<String, String>> userList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("NAME", "User" + i);
            userInfo.put("EMAIL", "aa@bb.com");
            userList.add(userInfo);
        }
        return userList;
    }
}
