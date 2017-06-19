package first.ood.srp;

import java.util.List;

/**
 * Created by william on 2017/6/12.
 */
public class SendMailTest {

    public static void main(String[] args) {
        String sql = "Select name from subscriptions where product_id= ? and send_mail=1";
        String filepath = "D:\\workspace\\IDEA\\homework\\coding2017_section2\\coding2017\\students\\785396327\\first\\ood\\srp\\product_promotion.txt";
        boolean isDebug = false;

        ConfigParser configParser = new PromotionMailConfigParser();
        FileParser fileParser = new PromotionFileParser(filepath);
        DBParser<PromotionMail> DBParser = new PromotionMailDBParser(sql, null);

        EmailParser emailParser = new EmailParser(configParser, fileParser, DBParser);
        List promotionMails = emailParser.parseEmailList();
        MailSender<PromotionMail> mailSender = new MailSender();
        mailSender.sendMailList(promotionMails, isDebug);
    }
}
