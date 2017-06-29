package com.coderising.ood.srp;

/**
 * 发送邮件
 *
 * @author nvarchar
 *         date 2017/6/26
 */
public class MailService {
    private String fromAddress;
    private String smtpHost;
    private String altSmtpHost;

    public MailService(Configuration config) {
        this.fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
        this.smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
        this.altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
    }

    public void sendMail(Mail mail) {
        try {
            sendEmail(mail, this.smtpHost);
        } catch (Exception e) {
            try {
                sendEmail(mail, this.altSmtpHost);
            } catch (Exception ex) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + ex.getMessage());
            }

        }
    }

    private void sendEmail(Mail mail, String smtpHost) {
        String toAddress = mail.getAddress();
        String subject = mail.getSubject();
        String msg = mail.getBody();
        //发送邮件
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(toAddress).append("\n");
        buffer.append("Subject:").append(subject).append("\n");
        buffer.append("Content:").append(msg).append("\n");
        System.out.println(buffer.toString());
    }
}
