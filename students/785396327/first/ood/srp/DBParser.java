package first.ood.srp;

import java.util.HashMap;
import java.util.List;

/**
 * Created by william on 2017/6/14.
 */
public abstract class DBParser<T extends Email> {
    protected String sql;
    protected Object[] params;

    protected DBParser(String sql, Object[] params) {
        this.sql = sql;
        this.params = params;
    }

    protected List<T> parseInfoFromDB(T email) {
        List<HashMap<String, String>> data = DBUtil.query(sql, params);
        return convertData(email, data);
    }

    abstract List<T> convertData(T email, List<HashMap<String, String>> data);
}
