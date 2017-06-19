package srp;

import srp.model.Product;
import srp.model.User;
import srp.service.ProductService;
import srp.service.PromotionService;
import srp.service.UserService;
import srp.util.RandomUtils;

import java.util.List;

/**
 * @version V1.0
 * @Title： Main
 * @Package： srp
 * @Description： 主程序
 * @author： 南来
 * @date： 2017-06-12 9:22
 */
public class Main {

    /**
     * //TODO 写的crud项目太多，作业越写越懵逼，最后我也不知道写成啥了，也不知道是否符合SRP原则。。。总之……欢迎老师和同学们批评指正！
     */
    public static void main(String[] args) throws InterruptedException {
        //模拟业务场景
        for (; ; ) {
            start();
            Thread.sleep(Long.parseLong(RandomUtils.randomNumber(3)));
        }
    }

    private static void start() {
        //region 模拟自动装配
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        PromotionService promotionService = new PromotionService();
        //endregion

        // 1 遍历商品 是否降价
        List<Product> products = productService.getProduct();
        if (null != products) {
            for (Product product : products) {
                // 2 商品降价
                if (product.getDown()) {
                    // 3 获取所有关注这个产品的用户
                    List<User> watchProductUsers = userService.getWatchProductUsers(product.getId());
                    if (null != watchProductUsers && watchProductUsers.size() > 0)
                        // 4 发送促销邮件
                        for (User user : watchProductUsers)
                            promotionService.promotionMail(user.getName(), user.getEmail(), product.getDesc());
                }
            }
        }

        //TODO 以上代码有明显线程问题。。。大家无视就好。。。。。¯\_(ツ)_/¯
    }
}
