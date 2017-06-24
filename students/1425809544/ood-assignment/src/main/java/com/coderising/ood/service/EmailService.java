package com.coderising.ood.service;

import com.coderising.ood.pojo.Email;
import com.coderising.ood.pojo.EmailServiceConfig;
import com.coderising.ood.pojo.Product;
import com.coderising.ood.pojo.User;

import java.io.IOException;

/**
 * @author xyy
 * @create 2017-06-19 9:44
 **/
public class EmailService {


    public static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
                                 boolean debug) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(toAddress).append("\n");
        buffer.append("Subject:").append(subject).append("\n");
        buffer.append("Content:").append(message).append("\n");
        System.out.println(buffer.toString());

    }


    public static Email configureEMail(User user, Product product) throws IOException {
        String toAddress = user.getEmail();
        String name = "";
        if (toAddress.length() > 0) {
            name = user.getName();
        }
        String subject = "您关注的产品降价了";
        String message = "尊敬的 " + name + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!";
        Email email = new Email(toAddress, subject, message);
        return email;
    }

    public static void sendEmail(EmailServiceConfig emailServiceConfig, Email email, boolean debug) {
        try {
            if (email.getToAddress().length() > 0) {
                sendEmail(email.getToAddress(), emailServiceConfig.getFromAddress(), email.getSubject(), email.getMessage(), emailServiceConfig.getSmtpHost(), debug);
            }
        } catch (Exception e) {
            try {
                sendEmail(email.getToAddress(), emailServiceConfig.getFromAddress(), email.getSubject(), email.getMessage(), emailServiceConfig.getAltSmtpHost(), debug);
            } catch (Exception e2) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
            }
        }
    }
}
