package srp.service;

import srp.dao.DB;
import srp.model.Product;

import java.io.IOException;
import java.util.List;

/**
 * @version V1.0
 * @Title： ProductService
 * @Package： srp.service
 * @Description： 商品service
 * @author： 南来
 * @date： 2017-06-12 11:11
 */
public class ProductService {

    //模拟自动装配
    private DB db = new DB();

    /**
     * 获取所有商品
     *
     * @return 所有商品
     */
    public List<Product> getProduct() {
        try {
            return db.getProducts();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
