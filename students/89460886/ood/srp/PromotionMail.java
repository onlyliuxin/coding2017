package ood.srp;

import java.io.File;
import java.util.List;

public class PromotionMail {

    public static void main(String[] args) throws Exception {

        File file = new File("/Users/jiaxun/OpenSource/Algorithm/src/ood/srp/product_promotion.txt");
        boolean emailDebug = false;

        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        Product product = ProductRepository.getProductFromFile(file);

        List<User> userList = DBUtil.queryUserListByProduct(product);

        PromotionMail pe = new PromotionMail();

        pe.sendEMails(emailDebug, userList, product);
    }

    protected void sendEMails(boolean debug, List<User> userList, Product product) {

        System.out.println("开始发送邮件");

        if (userList != null) {
            for (int i = 0, len = userList.size(); i < len; i++) {
                MailRequest mailRequest = new MailRequest();

                mailRequest.setSubject("您关注的产品降价了");
                mailRequest.setMessage("尊敬的 " + userList.get(i).getName() + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!");
                mailRequest.setToAddress(userList.get(i).getEmail());

                SmtpClient.sharedInstance().sendEmail(mailRequest, debug);
            }
        } else {
            System.out.println("没有邮件发送");
        }
    }

}
