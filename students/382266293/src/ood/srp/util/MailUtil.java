package ood.srp.util;

import ood.srp.bean.Mail;
import ood.srp.config.ServerConfig;

public class MailUtil {

    public static void send(Mail mail, ServerConfig sc) {
        try {
            sendEmail(mail, sc.getSmtpHost());
        } catch (Exception e) {
            try {
                sendEmail(mail, sc.getAltSmtpHost());
            } catch (Exception e2) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
            }
        }
    }

    public static void sendEmail(Mail mail, String SmtpHost) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(mail.getFromAddress()).append("\n");
        buffer.append("To:").append(mail.getSubscriber().getMailAddress()).append("\n");
        buffer.append("Subject:").append(mail.getSubject()).append("\n");
        buffer.append("Content:").append(mail.getMessage()).append("\n");
        System.out.println(buffer.toString());

    }


}
