package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {

    private Product product;

    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";

    public PromotionMail() {
        File f = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
        try {
            product = Product.of(f);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        PromotionMail pe = new PromotionMail();
        List emailList = pe.loadMailingList();
        pe.sendEMails(emailList);
    }


    protected List loadMailingList() throws Exception {
        String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + product.getProductID() + "' "
                + "and send_mail=1 ";
        return DBUtil.query(sendMailQuery);
    }


    protected void sendEMails(List mailingList) throws IOException {

        System.out.println("开始发送邮件");
        Mail mail = new Mail(new Configuration());

        if (mailingList != null) {
            Iterator iter = mailingList.iterator();
            while (iter.hasNext()) {
                HashMap userInfo = (HashMap) iter.next();
                mail.setToAddress((String) userInfo.get(EMAIL_KEY));
                setContent(mail, userInfo, product);
                try {
                    mail.send();
                } catch (Exception e) {
                    try {
                        mail.send();
                    } catch (Exception e2) {
                        System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                    }
                }
            }
        } else {
            System.out.println("没有邮件发送");
        }
    }

    private void setContent(Mail mail, HashMap userInfo, Product product) {
        String subject = "您关注的产品降价了";
        String name = (String) userInfo.get(NAME_KEY);
        String message = "尊敬的 " + name + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!";
        mail.setContent(subject, message);
    }
}
