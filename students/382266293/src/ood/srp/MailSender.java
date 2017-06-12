package ood.srp;

import ood.srp.bean.Mail;
import ood.srp.bean.Product;
import ood.srp.config.Configuration;
import ood.srp.config.ServerConfig;
import ood.srp.dao.MailDAO;
import ood.srp.dao.ProductDAO;
import ood.srp.util.MailUtil;

import java.io.File;
import java.util.List;

/**
 * Created by onlyLYJ on 2017/6/12.
 */
public abstract class MailSender {

    protected static ServerConfig sc = new Configuration().config().getServerConfig();
    protected static MailDAO mailDAO = new MailDAO();
    protected static ProductDAO productDAO = new ProductDAO();
    protected boolean debug;

    protected List<Mail> prepareMails(File productFile) throws Exception {

        List<Product> products = productDAO.list(productFile);
        List<Mail> mailingList = mailDAO.loadMailingList(products);
        setMailContext(mailingList);

        return mailingList;
    }

    protected abstract void setMailContext(List<Mail> mailingList);

    protected void setDebug(boolean debug) {
        this.debug = debug;
    }

    protected void sendEMails(List<Mail> mailingList) {

        if (mailDAO.isValide(mailingList)) {
            System.out.println("开始发送邮件");

            for (Mail mail : mailingList) {
                MailUtil.send(mail, sc);
            }
        }

    }


}
