package first.ood.srp;

import java.util.List;

/**
 * Created by gongxun on 2017/6/12.
 */
public class SendMailTest {

    public static void main(String[] args) {
        String loadQuery = "Select name from subscriptions where product_id= ? and send_mail=1";
        String filepath = "D:\\workspace\\IDEA\\homework\\coding2017_section2\\coding2017\\students\\785396327\\first\\ood\\srp\\product_promotion.txt";
        boolean isDebug = false;

        EmailParser emailParser = new EmailParser();
        List<PromotionMail> promotionMails = emailParser.parseEmailList(filepath, loadQuery);
        MailSender mailSender = new MailSender();
        mailSender.sendMailList(promotionMails, isDebug);
    }
}
