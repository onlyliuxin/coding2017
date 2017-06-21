package com.coderising.ood.srp;

import java.util.List;

public class MailUtil {

	public static void sendEmail(List<MailInfo> mailInfoList, boolean debug) {
	    if (mailInfoList == null && mailInfoList.size() == 0) {
            System.out.println("无邮件发送");
            return;
        }
        System.out.println("开始发送邮件");
        for (MailInfo mailInfo : mailInfoList) {
	        sendEmail(mailInfo, debug);
        }
    }

	public static void sendEmail(MailInfo mailInfo, boolean debug) {
	    Configuration configuration = new Configuration();
	    String smtpServer = configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
	    String altSmtpServer = configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
        String emailAdmin = configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
        try {
            send(mailInfo.getToAddress(), emailAdmin, mailInfo.getSubject(), mailInfo.getMessage(), smtpServer, debug);
        } catch (Exception e) {
            try {
                send(mailInfo.getToAddress(), emailAdmin, mailInfo.getSubject(), mailInfo.getMessage(), altSmtpServer, debug);
            } catch (Exception e2) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
            }
        }
    }

    public static void send(String toAddress, String fromAddress, String subject, String message, String smtpHost,
        boolean debug) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(toAddress).append("\n");
        buffer.append("Subject:").append(subject).append("\n");
        buffer.append("Content:").append(message).append("\n");
        System.out.println(buffer.toString());
    }
}
