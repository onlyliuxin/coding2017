package com.coderising.ood.srp;

import com.coderising.ood.srp.dto.Mail;
import com.coderising.ood.srp.dto.Product;
import com.coderising.ood.srp.dto.User;
import com.coderising.ood.srp.util.DBUtil;
import com.coderising.ood.srp.util.MailUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {

    protected Product product = new Product();

    private static Configuration config;

    public static void main(String[] args) throws Exception {

        File f = new File("/Users/guodongchow/Desktop/coding2017/projects/ood/ood-assignment/src/main/java/com/coderising/ood/srp/product_promotion.txt");
        boolean emailDebug = false;

        PromotionMail pe = new PromotionMail(f, emailDebug);

    }

    public PromotionMail(File file, boolean mailDebug) throws Exception {

        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        readFile(file);

        config = Configuration.getInstance();

        sendEMails(mailDebug, loadMailingList());
    }

    protected void readFile(File file) throws IOException // @02C
    {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            String[] data = temp.split(" ");

            product.setProductID(data[0]);
            product.setProductDesc(data[1]);

            System.out.println("产品ID = " + product.getProductID());
            System.out.println("产品描述 = " + product.getProductDesc() + "\n");

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
    }

    protected Mail configureEMail(User user, Product product) throws IOException {
        return new Mail(user, product);
    }

    protected List loadMailingList() throws Exception {
        return DBUtil.query();
    }


    protected void sendEMails(boolean debug, List mailingList) throws IOException {

        System.out.println("开始发送邮件"+ ":\n");


        if (mailingList != null) {
            Iterator iter = mailingList.iterator();
            while (iter.hasNext()) {
                Mail mail = configureEMail((User) iter.next(), product);
                if (mail.getToAddress().length() > 0)
                    MailUtil.sendEmail(mail, config, debug);
            }

        } else {
            System.out.println("没有邮件发送");

        }
    }
}
