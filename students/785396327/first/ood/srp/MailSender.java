package first.ood.srp;

import java.util.Iterator;
import java.util.List;

/**
 * Created by william on 2017/6/12.
 */
public class MailSender<T extends Email> {

    private void sendMail(Email mail, boolean isDebug) {
        if (!StringUtils.isEmpty(mail.toAddress))
            try {
                MailUtil.sendEmail(
                        mail.toAddress,
                        mail.fromAddress,
                        mail.subject,
                        mail.message,
                        StringUtils.isEmpty(mail.smtpHost) == true ? mail.smtpHost : mail.altSmtpHost,
                        isDebug);
            } catch (Exception e) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e.getMessage());
            }
    }

    public void sendMailList(List<T> mailList, boolean isDebug) {
        if (mailList != null) {
            for (Iterator<T> iterator = mailList.iterator(); iterator.hasNext(); ) {
                sendMail(iterator.next(), isDebug);
            }
        }
    }
}
