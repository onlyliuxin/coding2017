package com.coderising.ood.srp.refactor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by walker on 2017/6/20.
 */

public class EmailUtils {

    private static final String SUBJECT = "您关注的产品降价了";
    private static String smtpHost;
    private static String altSmtpHost;
    private static boolean debug = false;

    private static List<Email> getEmails() {
        List<Email> emails = new ArrayList<>();

        List<Consumer> consumers = ConsumerUtils.getConsumers();
        Iterator iter = consumers.iterator();

        Configuration conf = new Configuration();

        smtpHost = conf.getProperty(ConfigurationKeys.SMTP_SERVER);
        altSmtpHost = conf.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);

        while (iter.hasNext()) {
            Consumer consumer = (Consumer) iter.next();
            String message = PhoneUtils.getMessage(consumer.getName());
            String fromAddress = conf.getProperty(ConfigurationKeys.EMAIL_ADMIN);
            String toAddress = consumer.getEmail();
            Email email = new Email(SUBJECT, message, fromAddress, toAddress);
            emails.add(email);
        }

        return emails;
    }

    private static void sendEmail(Email email, String smtpHost,
                                 boolean debug) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(email.getFromAddress()).append("\n");
        buffer.append("To:").append(email.getToAddress()).append("\n");
        buffer.append("Subject:").append(email.getSubject()).append("\n");
        buffer.append("Content:").append(email.getMessage()).append("\n");
        System.out.println(buffer.toString());
    }

    public static void doSendEmail () {

        List<Email> emails = getEmails();
        for (Email email : emails) {
            try {
                sendEmail(email, smtpHost, debug);
            } catch (Exception e) {
                try {
                    sendEmail(email, altSmtpHost, debug);

                } catch (Exception e2) {
                    System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                }
            }
        }
    }
}
