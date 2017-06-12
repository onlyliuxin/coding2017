package first.ood.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {

    /**
     * 应该从数据库读， 但是简化为直接生成。
     *
     * @param sql
     * @return
     */
    public static List<HashMap<String, String>> query(String sql, Object[] params) {
        formateSQL(sql, params);

        List userList = new ArrayList();
        for (int i = 1; i <= 3; i++) {
            Map<String, String> userInfo = new HashMap();
            userInfo.put("NAME", "User" + i);
            userInfo.put("EMAIL", "aa@bb.com");
            userList.add(userInfo);
        }

        return userList;
    }

    private static String formateSQL(String sql, Object[] params) {
        if (StringUtils.isEmpty(sql))
            throw new RuntimeException("empty sql");
        String[] sqlFaction = sql.split("\\?");
        if (sqlFaction.length - 1 != params.length)
            throw new RuntimeException("wrong number of parameters");
        for (int i = 0; i < params.length; i++) {
            sql = sql.replaceFirst("\\?", "'" + params[i].toString() + "'");
        }

        return sql;
    }

}
