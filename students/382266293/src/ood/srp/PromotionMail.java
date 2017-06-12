package ood.srp;

import ood.srp.bean.Mail;

import java.io.File;
import java.util.List;

public class PromotionMail extends MailSender {

    private static final String EMAIL_ADMIN = "email.admin";

    public static void main(String[] args) throws Exception {

        File f = new File("E:\\git\\coding2017\\students\\382266293\\src\\com\\coderising\\ood\\srp\\data\\product_promotion.txt");
        MailSender pm = new PromotionMail();
        pm.setDebug(false);
        List<Mail> mailList = pm.prepareMails(f);
        pm.sendEMails(mailList);

    }

    @Override
    protected void setMailContext(List<Mail> mailingList) {

        String subject = "您关注的产品降价了";

        for (Mail mail : mailingList) {
            mail.setFromAddress(EMAIL_ADMIN);
            mail.setSubject(subject);
            String name = mail.getSubscriber().getName();
            String productDesc = mail.getProduct().getProductDesc();
            String message = "尊敬的 " + name + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";
            mail.setSubject(subject);
            mail.setMessage(message);
        }

    }


}
