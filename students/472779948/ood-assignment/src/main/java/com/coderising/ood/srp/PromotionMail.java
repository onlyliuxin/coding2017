package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {

    protected Mail mail = null;
    protected Product product = null;

    private static Configuration config;

    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";

    public static void main(String[] args) throws Exception {

        File f = new File("E:\\workspace\\private\\projects\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
        boolean emailDebug = false;

        PromotionMail pe = new PromotionMail(f, emailDebug);

    }


    public PromotionMail(File file, boolean mailDebug) throws Exception {
        config = new Configuration();

        //构造Mail对象
        mail = new Mail();
        mail.setSmtpHost(config.getProperty(ConfigurationKeys.SMTP_SERVER));
        mail.setAltSmtpHost(config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
        mail.setFromAddress(config.getProperty(ConfigurationKeys.EMAIL_ADMIN));

        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        String[] data = FileManager.readFile(file);
        product = new Product(data[0],data[1]);

        sendEMails(mailDebug, MailUtil.loadMailingList(product.getproductID()));//MailUtil
    }

    protected void setMessage(HashMap userInfo) throws IOException {
        String name = (String) userInfo.get(NAME_KEY);
        mail.setSubject("您关注的产品降价了");
        mail.setMessage("尊敬的 " + name + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!");

    }

    protected void configureEMail(HashMap userInfo) throws IOException {
        mail.setToAddress((String) userInfo.get(EMAIL_KEY));
        if (mail.getToAddress().length() > 0)
            setMessage(userInfo);
    }

    protected void sendEMails(boolean debug, List mailingList) throws IOException {
        System.out.println("开始发送邮件");
        if (mailingList != null) {
            Iterator iter = mailingList.iterator();
            while (iter.hasNext()) {
                configureEMail((HashMap) iter.next());
                try {
                    if (mail.getToAddress().length() > 0)
                        MailUtil.sendEmail(mail, debug);
                } catch (Exception e) {

                    try {
                        MailUtil.sendEmail(mail, debug);

                    } catch (Exception e2) {
                        System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                    }
                }
            }
        } else {
            System.out.println("没有邮件发送");

        }
    }
}
