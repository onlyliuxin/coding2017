package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * DBUtil
 *
 * @author Chenpz
 * @package com.coderising.ood.srp
 * @date 2017/6/12/23:32
 */
public class DBUtil {
    /**
     * 应该从数据库读， 但是简化为直接生成。
     * @param sql
     * @return
     */
    public static List query(String sql){

        List userList = new ArrayList();
        for (int i = 1; i <= 3; i++) {
            HashMap userInfo = new HashMap();
            userInfo.put("NAME", "User" + i);
            userInfo.put("EMAIL", "aa@bb.com");
            userList.add(userInfo);
        }
        return userList;
    }
}
