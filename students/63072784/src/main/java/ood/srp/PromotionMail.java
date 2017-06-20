package ood.srp;

import java.io.File;
import java.util.List;

public class PromotionMail {


    private static final String SUBJECT = "您关注的商品降价了";

    public static void main(String[] args) throws Exception {
        //这里可以做成参数输入
        File f = new File(PromotionMail.class.getClassLoader().getResource("product_promotion.txt").toURI());
        boolean debug = true;
        PromotionMail pe = new PromotionMail();
        pe.sendEMails(f, debug);
    }


    public PromotionMail() {
    }

    private String buildMessage(UserInfo userInfo, Product product) {
        return String.format("尊敬的 %s, 您关注的产品 %s 降价了，欢迎购买!", userInfo.getName(), product.getProductDesc());
    }

    private Mail buildMail(MailAccount mailAccount, UserInfo userInfo, Product product) {
        //可以更加详细的检查
        if (mailAccount == null || userInfo == null || product == null) {
            return null;
        } else {
            String message = buildMessage(userInfo, product);
            return new Mail(mailAccount, userInfo.getEmailAddress(), SUBJECT, message);
        }
    }


    private void sendEMails(File file, boolean debug) throws Exception {

        //构建发送邮件账号
        MailAccount mailAccount = MailAccount.buildAccount(new Configuration());

        //读取降价列表
        Product product = Product.getPromotionProduct(file);

        //获取订阅用户列表
        List<UserInfo> userInfoList = UserInfo.getUserInfo(product.getProductId());

        System.out.println("开始发送邮件");

        if (userInfoList != null) {
            for (UserInfo userInfo : userInfoList) {
                Mail mail = buildMail(mailAccount, userInfo, product);
                if (mail == null) {
                    continue;
                }
                MailUtil.sendEmail(mail, debug);
            }
        } else {
            System.out.println("没有邮件发送");

        }
    }
}
