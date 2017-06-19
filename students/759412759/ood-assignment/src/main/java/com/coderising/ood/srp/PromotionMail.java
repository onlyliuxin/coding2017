package com.coderising.ood.srp;

import java.io.*;
import java.util.*;

public class PromotionMail {

    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";


    public static void main(String[] args) throws Exception {

        UserService userService = new UserService();
        PromotionMail pe = new PromotionMail();

        String path = "F:\\IDEA_PRO_01\\coderrising\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt";
        Product product = ProductService.loadProductFromFile(path);
        List<HashMap<String, String>> list = userService.loadMailingList(product.getProductID());

        pe.sendEMails(list,product,Boolean.FALSE);
    }

    private void sendEMails(List<HashMap<String, String>> mailingList, Product product, boolean debug) throws IOException {
        System.out.println("开始发送邮件");
        if (mailingList != null) {
            Iterator<HashMap<String, String>> iter = mailingList.iterator();
            while (iter.hasNext()) {
                HashMap<String, String> userInfo = iter.next();
                Mail mail = getMail(product, debug, userInfo);
                try {
                    boolean flag = MailUtil.sendEmail(mail);
                    if (!flag) {
                        mail.setSmtpHost(mail.getAltSmtpHost());
                        MailUtil.sendEmail(mail);
                    }
                } catch (Exception e) {
                    try {
                        mail.setSmtpHost(mail.getAltSmtpHost());
                        MailUtil.sendEmail(mail);
                    } catch (Exception e2) {
                        System.out.println("通过备用 SMTP 服务器发送邮件失败: " + e2.getMessage());
                    }
                }
            }
        } else {
            System.out.println("没有邮件发送");
        }
    }

    private Mail getMail(Product product, boolean debug, HashMap<String, String> userInfo) {
        Mail mail = new Mail();
        String subject = "您关注的产品降价了";
        String message = "尊敬的 " + userInfo.get(NAME_KEY) + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!";

        mail.init();
        mail.setToAddress(userInfo.get(EMAIL_KEY));
        mail.setSubject(subject);
        mail.setMessage(message);
        mail.setDebug(debug);
        return mail;
    }
}
