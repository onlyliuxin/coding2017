package ood.srp.dao;

import ood.srp.bean.Mail;
import ood.srp.bean.Product;
import ood.srp.bean.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by onlyLYJ on 2017/6/12.
 */
public class MailDAO {

    public List<Mail> loadMailingList(List<Product> products) throws Exception {

        List<Mail> mails = new ArrayList<>();

        for (Product p : products) {
            List<Subscriber> subscribers = new UserDAO().list(p);

            for (Subscriber s : subscribers) {
                Mail mail = new Mail();
                mail.setProduct(p);
                mail.setSubscriber(s);
                mails.add(mail);
            }
        }
        return mails;
    }

    public boolean isValide(List<Mail> mailingList) {
        if (null == mailingList || mailingList.isEmpty()) {
            throw new RuntimeException("没有邮件发送");
        }
        return true;
    }

}
