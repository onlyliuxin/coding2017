package srp.service;

import srp.dao.DB;
import srp.model.User;

import java.util.List;

/**
 * @version V1.0
 * @Title： UserService
 * @Package： srp.service
 * @Description： User服务类 省去接口部分
 * @author： 南来
 * @date： 2017-06-12 10:25
 */
public class UserService {
    //模拟自动装配
    private DB db = new DB();

    /**
     * 获取所有用户
     *
     * @return 所有用户
     */
    protected List<User> getUser() {
        return db.getUsers();
    }

    /**
     * @param productId 商品id
     * @return 所有关注该商品的用户集
     */
    public List<User> getWatchProductUsers(String productId) {
        return db.getWatchProductUsers(productId);
    }
}
