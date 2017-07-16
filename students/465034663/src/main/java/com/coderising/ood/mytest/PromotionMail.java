package com.coderising.ood.mytest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {


    protected String sendMailQuery;


    protected String smtpHost;
    protected String altSmtpHost;
    protected String fromAddress;
    protected String toAddress;
    protected String subject;
    protected String message;

    protected String productID;
    protected String productDesc;

    private Email email;

    private static Configuration config;


    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";


    public static void main(String[] args) throws Exception {

        File f = new File("D:\\IdeaWorspace\\works\\coding2017\\students\\465034663\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
        boolean emailDebug = false;

        PromotionMail pe = new PromotionMail(f, emailDebug);

    }


    public PromotionMail(File file, boolean mailDebug) throws Exception {

        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        IOUtils.readFile(file);


        config = new Configuration();

        /*setSMTPHost();
        setAltSMTPHost();*/
        this.smtpHost = new SMTPHost().setHost();
        this.altSmtpHost = new AltSMTPHost().setHost();

        setFromAddress();


        //setLoadQuery();

        DBUtil.loadQuery(this.productID);
        sendEMails(mailDebug, loadMailingList());


    }

    protected void setFromAddress() {
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    protected void setMessage(HashMap userInfo) throws IOException {

        String name = (String) userInfo.get(NAME_KEY);

        subject = "您关注的产品降价了";
        message = "尊敬的 " + name + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";


    }

    protected void configureEMail(HashMap userInfo) throws IOException {
        toAddress = (String) userInfo.get(EMAIL_KEY);
        if (toAddress.length() > 0)
            setMessage(userInfo);
    }

    protected List loadMailingList() throws Exception {
        return DBUtil.query(this.sendMailQuery);
    }


    protected void sendEMails(boolean debug, List mailingList) throws IOException {

        System.out.println("开始发送邮件");


        if (mailingList != null) {
            Iterator iter = mailingList.iterator();
            while (iter.hasNext()) {
                configureEMail((HashMap) iter.next());
                setEmail();
                try {
                    if (toAddress.length() > 0)
                        MailUtil.sendEmail(this.email, debug);
                } catch (Exception e) {

                    try {
                        MailUtil.sendEmail(this.email, debug);

                    } catch (Exception e2) {
                        System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                    }
                }
            }


        } else {
            System.out.println("没有邮件发送");

        }

    }

    private void setEmail(){
        this.email = new Email(this.toAddress, this.fromAddress, this.subject, this.message, this.altSmtpHost);
    }
}
