package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PromotionMail {


    protected String sendMailQuery = null;


    protected String smtpHost = null;
    protected String altSmtpHost = null;
    protected String fromAddress = null;
    protected String toAddress = null;
    protected String subject = null;
    protected String message = null;

    protected String productID = null;
    protected String productDesc = null;

    private static Configuration config;


    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";


    public static void main(String[] args) throws Exception {

        File f = new File("/Users/nvarchar/Documents/github/coding2017-2/" +
                "students/2816977791/ood/ood-assignment/src/main/java/com/coderising/ood/srp/product_promotion.txt");
        boolean emailDebug = false;

        PromotionMail pe = new PromotionMail(f);

        MailService.sendEMails(emailDebug, pe.loadMailingList(), pe);
    }


    public PromotionMail(File file) throws Exception {

        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        String[] data = FileUtil.readFile(file);

        setProductID(data[0]);
        setProductDesc(data[1]);

        config = new Configuration();

        setSMTPHost();

        setAltSMTPHost();

        setFromAddress();

        setLoadQuery();

    }


    protected void setProductID(String productID) {
        this.productID = productID;
        System.out.println("产品ID = " + productID + "\n");
    }

    protected String getproductID() {
        return productID;
    }

    protected void setLoadQuery() throws Exception {

        sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + productID + "' "
                + "and send_mail=1 ";


        System.out.println("loadQuery set");
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

    protected void setMessage(HashMap userInfo) throws IOException {

        String name = (String) userInfo.get(NAME_KEY);

        subject = "您关注的产品降价了";
        message = "尊敬的 " + name + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";

    }

    private void setProductDesc(String desc) {
        this.productDesc = desc;
        System.out.println("产品描述 = " + productDesc + "\n");
    }


    public void configureEMail(HashMap userInfo) throws IOException {
        toAddress = (String) userInfo.get(EMAIL_KEY);
        if (toAddress.length() > 0)
            setMessage(userInfo);
    }

    protected boolean hasValidToAddress() {
        if (toAddress.length() > 0) {
            return true;
        }
        return false;
    }

    protected List loadMailingList() throws Exception {
        return DBUtil.query(this.sendMailQuery);
    }


}
