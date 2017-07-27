package ood.work.srp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17 0017.
 */
public class Emails {

    public static Email newEmail(User user, Product product) {
        String subject = "您关注的产品降价了";
        String message = "尊敬的 "+user.getName()+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;
        return new Email(user.getEmail(), subject, message);
    }

    public static List<Email> createEmails(String path) throws IOException {
        List<Product> products = ProductInfoLoader.readFile(path);
        List<Email> mails = new ArrayList<>();
        for (Product product : products) {
            String sql = getSql(product.getProductId());
            List<User> users = DBUtil.query(sql);
            for (User user : users) {
                Email mail = Emails.newEmail(user, product);
                mails.add(mail);
            }
        }
        return mails;
    }

    private static String getSql(String productId) {
        System.out.println("loadQuery set");
        return "Select name from subscriptions "
                + "where product_id= '" + productId +"' "
                + "and send_mail=1 ";
    }
}
