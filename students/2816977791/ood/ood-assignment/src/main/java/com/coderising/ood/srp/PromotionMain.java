package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author nvarchar
 *         date 2017/6/29
 */
public class PromotionMain {
    private ProductService productService = new ProductService();
    private UserService userService = new UserService();

    public void run(String filepath) throws IOException {

        Configuration cfg = new Configuration();

        List<Product> productList = productService.getProductLists(filepath);

        Set<User> users = new HashSet<>();
        for (Product product : productList) {
            List<User> userList = userService.getUsers(product);
            //todo 将userlist中的user和对应的product添加到users中
        }
        MailService mailService = new MailService(cfg);
        for (User user : users) {
            mailService.sendMail(new Mail(user));
        }
    }

    public static void main(String[] args) throws Exception {
        String filePath = "/Users/nvarchar/Documents/github/coding2017-2/" +
                "students/2816977791/ood/ood-assignment/src/main/java/com/coderising/ood/srp/product_promotion.txt";
        PromotionMain main = new PromotionMain();
        main.run(filePath);
    }
}
