package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PromotionMail {
    private Product product;
    private Mail mail = null;
    private List<UserInfo> mailList;


    public static void main(String[] args) throws Exception {

        File f = new File(PromotionMail.class.getClassLoader().getResource("product_promotion.txt").getPath());

        boolean emailDebug = false;

        PromotionMail pe = new PromotionMail(f, emailDebug);
        pe.sendEMails(emailDebug);

    }


    public PromotionMail(File file, boolean mailDebug) throws Exception {
        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        mail = new Mail(new Configuration());
        product = Product.buildProduct(file);

    }


    public void sendEMails(boolean debug) throws IOException {

        System.out.println("开始发送邮件");
        if (getMailList() != null) {
            getMailList().forEach(
                    userInfo -> {
                        if (userInfo.getEmail().length() > 0) {
                            try {
                                mail.sendEmail(userInfo.getEmail(), getSubject(), getMessage(userInfo.getName(), getProduct().getProductDesc()), debug);
                            } catch (Exception e) {
                                try {
                                    mail.sendAltEmail(userInfo.getEmail(), getSubject(), getMessage(userInfo.getName(), getProduct().getProductDesc()), debug);
                                } catch (Exception e1) {
                                    System.out.println("通过备用 SMTP服务器发送邮件失败: " + e1.getMessage());
                                }
                            }
                        }
                    }
            );
        } else {
            System.out.println("没有邮件发送");
        }


    }

    private String getSubject() {
        return "您关注的产品降价了";
    }

    private String getMessage(String username, String productDesc) {
        return "尊敬的 " + username + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";

    }

    public List<UserInfo> getMailList() {
        if (mailList == null) {
            try {
                return loadMailingList(loadQuery(getProduct().getProductID()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return this.mailList;
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private String loadQuery(String productID) throws Exception {

        String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + productID + "' "
                + "and send_mail=1 ";


        System.out.println("loadQuery set");
        return sendMailQuery;
    }


    private List<UserInfo> loadMailingList(String queryString) throws Exception {
        return DBUtil.query(queryString);
    }

}
