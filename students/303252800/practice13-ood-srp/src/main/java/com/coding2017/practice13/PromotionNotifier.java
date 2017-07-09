package com.coding2017.practice13;

import java.text.MessageFormat;

// 促销通知职责类
public class PromotionNotifier {

    private static String subject = "您关注的产品降价了";
    private static String message = "尊敬的 {1} , 您关注的产品 {2} 降价了，欢迎购买!";

    /**
     * 发送邮件通知
     *
     * @param product    促销产品
     * @param subscriber 订阅人
     */
    public static void sendEmail(PromotionProduct product, PromotionSubscriber subscriber) {
        System.out.println("开始发送邮件");
        String content = MessageFormat.format(message, subscriber.getSubscriber(), product.getProductDesc());
        if (subscriber.getToAddress().length() > 0) {
            try {
                sendEmail(EmailConfiguration.getFromAddress(), subscriber.getToAddress(), subject, content, EmailConfiguration.getSmtpServer());
            } catch (Exception e1) {
                try {
                    sendEmail(EmailConfiguration.getFromAddress(), subscriber.getToAddress(), subject, content, EmailConfiguration.getAltSmtpServer());
                } catch (Exception e2) {
                    System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                }
            }
        } else {
            System.out.println("没有邮件发送");
        }
    }

    /**
     * 执行发送邮件
     *
     * @param fromAddress 发件人地址
     * @param toAddress   收件人地址
     * @param subject     邮件标题
     * @param content     邮件内容
     * @param smtpHost    smtp服务器地址
     */
    private static void sendEmail(String fromAddress, String toAddress, String subject, String content, String smtpHost) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(toAddress).append("\n");
        buffer.append("Subject:").append(subject).append("\n");
        buffer.append("Content:").append(content).append("\n");
        System.out.println(buffer.toString());
    }

}
