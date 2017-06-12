package first.ood.srp;

import java.util.Iterator;
import java.util.List;

/**
 * Created by gongxun on 2017/6/12.
 */
public class MailSender {

    private void sendMail(PromotionMail mail, boolean isDebug) {
        MailUtil.sendEmail(mail.toAddress, mail.fromAddress, mail.subject, mail.message, StringUtils.isEmpty(mail.smtpHost) == true ? mail.smtpHost : mail.altSmtpHost, isDebug);
    }

    public void sendMailList(List<PromotionMail> mailList, boolean isDebug) {
        if (mailList != null) {
            for (Iterator<PromotionMail> iterator = mailList.iterator(); iterator.hasNext(); ) {
                sendMail(iterator.next(), isDebug);
            }
        }
    }
}
