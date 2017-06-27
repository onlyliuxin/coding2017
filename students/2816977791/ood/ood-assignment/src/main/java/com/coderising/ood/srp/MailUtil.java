package com.coderising.ood.srp;

public class MailUtil {

    public static void sendEmail(PromotionMail promotionMail, boolean debug) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(promotionMail.fromAddress).append("\n");
        buffer.append("To:").append(promotionMail.toAddress).append("\n");
        buffer.append("Subject:").append(promotionMail.subject).append("\n");
        buffer.append("Content:").append(promotionMail.message).append("\n");
        System.out.println(buffer.toString());
    }
}
