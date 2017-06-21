package com.coderising.ood.srp.utils;

import com.coderising.ood.srp.config.Configuration;
import com.coderising.ood.srp.config.ConfigurationKeys;

/**
 * MailUtil
 *
 * @author Chenpz
 * @package com.coderising.ood.srp
 * @date 2017/6/12/23:28
 */
public final class MailUtil {

    private static String smtpHost      = null;
    private static String altSmtpHost   = null;
    private static String fromAddress   = null;

    static{
        Configuration config = new Configuration();
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    private MailUtil(){
        throw new RuntimeException("illegal called!");
    }

    public static void sendEmail(String toAddress, String subject, String message, boolean debug) {

        //假装发了一封邮件
        System.out.println("debug: " + debug);
        try {
            System.out.println("使用默认host发送邮件");
            sendDefaultMail(toAddress, subject, message);
        }catch (Exception e){
            System.out.println("使用备用host发送邮件");
            sendAltMail(toAddress,subject, message);
        }
    }

    private static void sendDefaultMail(String toAddress, String subject, String message){
        System.out.println("smtpHost: " + smtpHost);
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(toAddress).append("\n");
        buffer.append("Subject:").append(subject).append("\n");
        buffer.append("Content:").append(message).append("\n");
        System.out.println(buffer.toString());
    }

    private static void sendAltMail(String toAddress, String subject, String message){
        System.out.println("altSmtpHost: " + altSmtpHost);
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(toAddress).append("\n");
        buffer.append("Subject:").append(subject).append("\n");
        buffer.append("Content:").append(message).append("\n");
        System.out.println(buffer.toString());
    }





}
