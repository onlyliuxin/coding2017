package ood.srp1.server;

import ood.srp1.util.DBUtil;
import java.util.List;
import java.util.Map;

/**
 * Created by lx on 2017/6/17.
 */
public class UserServer {

    /**
     * 查询发送人
     * @return
     */
    public List<Map> querySendUser(String productId){
        return DBUtil.query("Select name from subscriptions where product_id= '" + productId + "' and send_mail=1 ");
    }
}
