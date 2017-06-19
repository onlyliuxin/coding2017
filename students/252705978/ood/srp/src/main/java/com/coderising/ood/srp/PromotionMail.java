package com.coderising.ood.srp;

import java.io.File;

public class PromotionMail {

    protected String sendMailQuery = null;

    protected String smtpHost = null;
    protected String altSmtpHost = null;
    protected String fromAddress = null;
    protected String toAddress = null;
    protected String subject = null;
    protected String message = null;

    public PromotionMail() {
    };

    public static void main(String[] args) throws Exception {
        PromotionMail mail = new PromotionMail();
        Product product = new Product(FileUtil.readFile(new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt")));
        Configuration config = new Configuration();
        ConfigurationUtil.configure(mail, config);
        MailUtil.sendEMails(mail, config, false, product, DBUtil.query(mail.sendMailQuery));
    }

}
