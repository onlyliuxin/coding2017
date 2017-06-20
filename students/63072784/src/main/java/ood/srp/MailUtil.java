package ood.srp;

public class MailUtil {

    public static void sendEmail(Mail mail, boolean debug) {
        String toAddress = mail.getToAddress();
        String fromAddress = mail.getMailAccount().getAccount();
        String subject = mail.getSubject();
        String message = mail.getMessage();
        String smtpHost = mail.getMailAccount().getSmtpHost();
        String altSmtpHost = mail.getMailAccount().getAltSmtpHost();
        try {
            sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);
        } catch (Exception e) {
            try {
                MailUtil.sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug);

            } catch (Exception e2) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
            }
        }

    }

    private static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
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
