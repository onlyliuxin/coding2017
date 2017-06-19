package com.coderising.ood.service;

import com.coderising.ood.config.Configuration;
import com.coderising.ood.config.ConfigurationKeys;
import com.coderising.ood.pojo.Email;
import com.coderising.ood.pojo.EmailServiceConfig;
import com.coderising.ood.pojo.Product;
import com.coderising.ood.pojo.User;

import java.io.File;
import java.util.List;

public class PromotionMail {

    public static void main(String[] args) throws Exception {
        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        File file = new File("D:\\product_promotion.txt");
        //1.获得产品信息
        ProductService productService = new ProductService();
        List productList = productService.getAllProductFromFile(file);
        boolean emailDebug = false;

        PromotionMail pe = new PromotionMail(productList, emailDebug);
    }



    public PromotionMail(List productList, boolean mailDebug) throws Exception {
        //2.邮件服务器配置
        Configuration config = new Configuration();
        EmailServiceConfig emailServiceConfig = new EmailServiceConfig(config.getProperty(ConfigurationKeys.SMTP_SERVER), config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER), config.getProperty(ConfigurationKeys.EMAIL_ADMIN));
        //3.发送邮件
        sendEMails(mailDebug, productList, emailServiceConfig);

    }


    public void sendEMails(boolean debug, List<Product> productList, EmailServiceConfig emailServiceConfig) throws Exception {
        System.out.println("开始发送邮件");
        if (productList != null) {
            for (Product product : productList) {
                List<User> userList = UserService.getSendEmailUser(product);
                if (null != userList && userList.size() > 0) {
                    for (User user : userList) {
                        Email email = EmailService.configureEMail((user), product);
                        if (email.getToAddress().length() > 0) {
                            EmailService.sendEmail(emailServiceConfig,email,debug);
                        }
                    }
                }
            }

        } else {
            System.out.println("没有邮件发送");

        }

    }


}


