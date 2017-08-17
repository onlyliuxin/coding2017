package com.coderising.ood.srp;

public class MailUtil {

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


    public static void sendEmail(MailData mailData) {
        //假装发了一封邮件
        try {
            StringBuilder buffer = new StringBuilder();
            buffer.append("Smtp:").append(mailData.getSmtpHost()).append("\n");
            buffer.append("From:").append(mailData.getFromAddress()).append("\n");
            buffer.append("To:").append(mailData.getToAddress()).append("\n");
            buffer.append("Subject:").append(mailData.getSubject()).append("\n");
            buffer.append("Content:").append(mailData.getMessage()).append("\n");
            System.out.println(buffer.toString());
        } catch (Exception e) {
            try {
                StringBuilder buffer = new StringBuilder();
                buffer.append("Smtp:").append(mailData.getAltSmtpHost()).append("\n");
                buffer.append("From:").append(mailData.getFromAddress()).append("\n");
                buffer.append("To:").append(mailData.getToAddress()).append("\n");
                buffer.append("Subject:").append(mailData.getSubject()).append("\n");
                buffer.append("Content:").append(mailData.getMessage()).append("\n");
                System.out.println(buffer.toString());
            } catch (Exception e1) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e1.getMessage());
            }
        }


    }

}