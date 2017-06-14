package first.ood.srp;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gongxun on 2017/6/14.
 */
public abstract class DBParser<T> {
    private String sql;

    protected DBParser(String sql) {
        this.sql = sql;
    }

    protected List<T> parseInfoFromDB(Email email) {
        List<HashMap<String, String>> data = DBUtil.query(sql, null);
        return convertData(email, data);
    }

    abstract List<T> convertData(Email email, List<HashMap<String, String>> data);
}
