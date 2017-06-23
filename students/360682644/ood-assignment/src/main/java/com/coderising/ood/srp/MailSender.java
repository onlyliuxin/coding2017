package com.coderising.ood.srp;

import org.junit.Assert;

import java.io.*;
import java.util.*;

public class MailSender {

    protected MailHosts hosts = null;
    protected String fromAddress = null;
    protected InputStream is = null;
    protected List<MailReceiver> receivers = null;
    private Configuration config = Configuration.getInstance();

    public void init() throws Exception {
        setSMTPHost();
        setFromAddress();
    }

    protected MailSender setEmailInput(InputStream is) throws IOException {
        this.is = is;
        return this;
    }

    protected MailSender setReceiver() throws IOException {
        Assert.assertNotNull("is cannot be null", is);
        List<Product> products = read(is);
        receivers = new ArrayList();
        for (Product product : products) {
            receivers.addAll(ReceiverService.getInstance().loadMailingList(product));
        }
        return this;
    }

    protected void setSMTPHost() {
        hosts = new MailHosts();
    }

    protected void setFromAddress() {
        fromAddress = config.getProperty(Configuration.EMAIL_ADMIN);
    }

    protected List<Product> read(InputStream is) throws IOException {
        BufferedReader br = null;
        List<Product> products = new ArrayList();
        try {
            br = new BufferedReader(new InputStreamReader(is));
            String temp;
            while ((temp = br.readLine()) != null) {
                String[] data = temp.split(" ");
                Product product = new Product();
                product.setProductID(data[0]);
                product.setProductDesc(data[1]);
                products.add(product);
                System.out.println("产品ID = " + product.getProductID() + "\n");
                System.out.println("产品描述 = " + product.getProductDesc() + "\n");
            }
        } catch (IOException e) {
            throw e;
        } finally {
            br.close();
        }
        return products;
    }

    protected void send() throws IOException {
        System.out.println("开始发送邮件");
        if (receivers == null || receivers.isEmpty()) {
            System.out.println("没有邮件发送");
            return;
        }
        for (MailReceiver receiver : receivers) {
            MailUtil.sendEmail(receiver.getEmail(), fromAddress, receiver.getSubject(), receiver.getMessage(), hosts);
        }
    }

    public static void main(String[] args) throws Exception {

        File f = new File("D:\\coding2017\\students\\360682644\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
        MailSender pe = new MailSender();
        pe.init();
        pe.setEmailInput(new FileInputStream(f))
                .setReceiver()
                .send();
    }
}
