package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PromotionMail {

    protected String smtpHost = null;
    protected String altSmtpHost = null;
    protected String fromAddress = null;
    protected String toAddress = null;
    protected Mail mail = new Mail();
    protected Product product = null;
    protected boolean debug = false;
    protected List<User> mailingList = null;

    private static Configuration config;

    public static void main(String[] args) throws Exception {
        File f = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
        boolean emailDebug = false;
        PromotionMail pe = new PromotionMail(f, emailDebug);
        pe.sendEMails();
    }

    public PromotionMail(File file, boolean mailDebug) throws Exception {
        debug = mailDebug;
        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        loadProduct(file);
        loadConfig();
        loadMailingList();
    }

    protected void loadProduct(File file) throws Exception {
        String fileStr = FileUtil.readFile(file);
        String data[] = fileStr.split(" ");
        product = new Product(data[0], data[1]);
    }

    protected void loadMailingList() {
        mailingList = UserService.loadMailingListByProductId(product.getProductID());
    }

    protected void loadConfig() {
        config = new Configuration();
        setSMTPHost();
        setAltSMTPHost();
        setFromAddress();
    }

    protected void setSMTPHost() {
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
    }

    protected void setAltSMTPHost() {
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);

    }

    protected void setFromAddress() {
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    protected boolean validateToAddress() {
        return toAddress.length() > 0;
    }

    protected void setToAddress(String address) {
        toAddress = address;
    }

    protected void sendEMails() throws IOException {
        System.out.println("开始发送邮件");
        if (mailingList == null) {
            System.out.println("没有邮件发送");
            return;
        }
        for (User user : mailingList) {
            this.setToAddress(user.getEmail());
            if (!this.validateToAddress()) {
                continue;
            }
            //设置促销消息
            mail.setPromotionMessage(user.getName(), product.getProductDesc());
            try {
                MailUtil.sendEmail(toAddress, fromAddress, mail, smtpHost, debug);
            } catch (Exception e) {
                try {
                    MailUtil.sendEmail(toAddress, fromAddress, mail, altSmtpHost, debug);
                } catch (Exception e2) {
                    System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                }
            }
        }
    }
}
