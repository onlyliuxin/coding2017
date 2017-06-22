package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail extends Mail {
    protected Product product;
    private static Configuration config;

    public static void main(String[] args) throws Exception {
        File f = new File("E:\\LandWolf\\coding2017\\students\\466199956\\ood\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
        boolean emailDebug = false;
        PromotionMail pe = new PromotionMail(f, emailDebug);
    }


    public PromotionMail(File file, boolean mailDebug) throws Exception {
        product = new Product();
        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        readFile(file);
        config = new Configuration();

        setSmtpHost(config.getProperty(ConfigurationKeys.SMTP_SERVER));
        setAltSmtpHost(config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));


        setFromAddress(config.getProperty(ConfigurationKeys.EMAIL_ADMIN));

        setLoadQuery();

        sendEMails(mailDebug, loadMailingList());


    }


    protected void setLoadQuery() throws Exception {

        sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + product.getproductID() + "' "
                + "and send_mail=1 ";


        System.out.println("loadQuery set");
    }


    protected void setMessage(HashMap userInfo) throws IOException {

        String name = (String) userInfo.get(NAME_KEY);

        subject = "您关注的产品降价了";
        message = "尊敬的 " + name + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!";


    }


    protected void readFile(File file) throws IOException // @02C
    {
        String temp = FileUtil.readFile(file);
        String[] data = temp.split(" ");

        product.setProductID(data[0]);
        product.setProductDesc(data[1]);

        System.out.println("产品ID = " + product.getproductID() + "\n");
        System.out.println("产品描述 = " + product.getProductDesc() + "\n");
    }


    @Override
    protected void configureEMail(HashMap userInfo) throws IOException {

        toAddress = (String) userInfo.get(EMAIL_KEY);
        if (toAddress.length() > 0)
            this.setMessage(userInfo);
    }
}
