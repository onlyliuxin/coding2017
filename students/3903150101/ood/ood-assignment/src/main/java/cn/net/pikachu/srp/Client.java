package cn.net.pikachu.srp;

import cn.net.pikachu.srp.base.Mail;
import cn.net.pikachu.srp.base.PromotionMail;
import cn.net.pikachu.srp.dao.impl.ProductDaoInFileImpl;
import cn.net.pikachu.srp.dao.impl.UserDaoInMemoryImpl;
import cn.net.pikachu.srp.service.ProductService;
import cn.net.pikachu.srp.service.UserService;
import cn.net.pikachu.srp.service.impl.ProductServiceImpl;
import cn.net.pikachu.srp.service.impl.UserServiceImpl;

/**
 * 模拟客户端
 */
public class Client {

    /**
     * Default constructor
     */
    public Client() {
    }

    /**
     * 模拟一个触发的场景
     * @param args
     */
    public static void main(String[] args) {
        final String file = "/home/pikachu/src/java/study/coding2017/students/3903150101/ood/ood-assignment/src/main/java/com/coderising/ood/srp/product_promotion.txt";
        ProductService productService = new ProductServiceImpl(new ProductDaoInFileImpl(file));
        UserService userService = new UserServiceImpl(new UserDaoInMemoryImpl());
        productService.getPromtionProduct().forEach(product ->{
            System.out.println(product.getName()+"降价了");
            System.out.println("快来买啊");
            userService.getSubscribers(product).forEach(user ->{
                Mail promotionMail = new PromotionMail(user,product);
                promotionMail.send();
            });
        });
        System.out.println("所有邮件发送完毕");
        System.out.println("Done!");
    }

}