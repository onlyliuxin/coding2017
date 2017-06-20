package com.coderising.ood.srp;

import com.coderising.ood.srp.bean.Email;
import com.coderising.ood.srp.bean.Product;
import com.coderising.ood.srp.dao.EmailDAO;

import java.io.IOException;
import java.util.*;

public class PromotionMail {
    private static EmailDAO emailDAO = new EmailDAO();

    public static void main(String[] args) throws Exception {
        List<Email> emailList = getEmails("product_promotion.txt");
        for (Email email : emailList) {
            sendEmail(email);
        }

    }

    public static List<Email> getEmails(String file) throws IOException {
        List<Email> emailList = new ArrayList<Email>();
        List<Product> list = Product.getProductByFile(file);

        for (Product p : list) {
            String productId = p.getProductID();
            List<Map<String, String>> l = emailDAO.listSubscriptionsByProdoctId(productId);
            for (Map<String, String> userInfo : l) {
                String username = userInfo.get("NAME");
                String productDesc = p.getProductDesc();
                String fromAddr = Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
                List<String> toAddr = new ArrayList<String>();
                toAddr.add(userInfo.get("EMAIL"));
                String smtpServer = Configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
                Email email = new Email(fromAddr, toAddr, "\"您关注的产品降价了\"", "尊敬的 " + username + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!", smtpServer, fromAddr);
                emailList.add(email);
            }
        }
        return emailList;
    }

    public static void sendEmail(Email email) {
        if (email == null) {
            System.out.println("没有邮件发送");
        }
        try {
            email.send();
        } catch (EmailException e) {
            email.setSmtpServer(Configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
            try {
                email.send();
            } catch (EmailException e1) {
                System.out.println("使用备用服务器发送失败");
            }
        }
    }
}
