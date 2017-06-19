package srp.dao;

import srp.model.Product;
import srp.model.User;
import srp.util.RandomUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Title： DB
 * @Package： srp.dao
 * @Description： 伪dao层 持续输出数据 没有细分每个对象单独dao层的部分
 * @author： 南来
 * @date： 2017-06-12 9:20
 */
public class DB {

    /**
     * 模拟在数据库中查询用户
     *
     * @return 所有用户
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName(RandomUtils.randomName());
            user.setEmail(RandomUtils.randomMail());
            //region 模拟关注商品
            try {
                List<Product> products = getProducts();
                if (null != products && products.size() > 0)
                    user.setWatchProductId(RandomUtils.randomOne(products).getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //endregion
            users.add(user);
        }
        return users;
    }

    /**
     * @param productId 商品id
     * @return 所有关注该商品的用户集
     */
    public List<User> getWatchProductUsers(String productId) {
        if (0 == productId.length()) return null;
        List<User> users = getUsers();
        List<User> temp = new ArrayList<>();
        for (User user : users) {
            if (null != user.getWatchProductId() && productId.equals(user.getWatchProductId()))
                temp.add(user);
        }
        return temp;
    }

    /**
     * 模拟在数据库中查询商品
     *
     * @return 所有商品
     */
    public List<Product> getProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File("D:\\product_promotion.txt")))) {
            String temp;
            while (null != (temp = br.readLine())) {
                String[] data = temp.split(" ");
                Product product = new Product();
                product.setId(data[0]);
                product.setDesc(data[1]);
                //region 模拟降价
                product.setDown(RandomUtils.randomBoolean());
                //endregion
                products.add(product);
            }
        }
        return products;
    }
}
