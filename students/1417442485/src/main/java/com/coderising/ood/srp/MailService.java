package main.java.com.coderising.ood.srp;

public class MailService {

    public static void send(final MailMessage message, final MailSetting mailSetting) {
        if(message.getToAddress().length() == 0) return;
        try {
            MailUtil.sendEmail(message.getToAddress(), message.getFromAddress(), message.getSubject(),
                    message.getMessageBody(), MailSetting.smtpHost, mailSetting.emailDebug);
        } catch (Exception e) {
            try {
                MailUtil.sendEmail(message.getToAddress(), message.getFromAddress(), message.getSubject(),
                        message.getMessageBody(), MailSetting.altSmtpHost, mailSetting.emailDebug);
            } catch (Exception e2) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
            }
        }
    }
}
