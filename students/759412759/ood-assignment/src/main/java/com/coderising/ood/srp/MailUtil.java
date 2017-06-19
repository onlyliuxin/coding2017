package com.coderising.ood.srp;


public class MailUtil {

    public static boolean sendEmail(Mail mail) {
        //假装发了一封邮件
        if(mail.getToAddress().length() < 0){
            return Boolean.FALSE;
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(mail.getFromAddress()).append("\n");
        buffer.append("To:").append(mail.getToAddress()).append("\n");
        buffer.append("Subject:").append(mail.getSmtpHost()).append("\n");
        buffer.append("Content:").append(mail.getMessage()).append("\n");
        buffer.append("smtpHost:").append(mail.getSmtpHost()).append("\n");
        buffer.append("isDebug:").append(mail.getDebug() ? "1" : "0").append("\n");
        System.out.println(buffer.toString());
        return Boolean.TRUE;
    }

}
