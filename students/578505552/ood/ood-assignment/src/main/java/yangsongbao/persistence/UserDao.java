package yangsongbao.persistence;

import yangsongbao.utils.DBUtil;
import yangsongbao.model.ProductInfo;
import yangsongbao.model.UserInfo;

import java.util.List;

/**
 *
 * Created by songbao.yang on 2017/6/17.
 */
public class UserDao {

    String needPromotionMailSql = "Select name, email from subscriptions "
            + "where product_id = %s" + "and send_mail = 1";

    public List<UserInfo> queryUserNeedPromotionMail(ProductInfo productInfo){
        String sql = String.format(needPromotionMailSql, productInfo.getId());
        return DBUtil.query(sql);
    }
}
