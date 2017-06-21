package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {

    private Product product = new Product();
    private MailServer mailServer = new MailServer();
    private List<Mail> mailList = new ArrayList<Mail>();
    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";

    public static void main(String[] args) throws Exception {

        File f = new File("/Users/john/Documents/mygit_2/students/315863321/ood/ood-assignment/src/main/java/com/coderising/ood/srp/product_promotion.txt");
        boolean emailDebug = false;

        PromotionMail promotionMail = new PromotionMail();
        //配置产品
        Build build = new BuildProduct(promotionMail.product);
        build.setReader(new ReadFromFile(f));
        build.build();

        //配置邮件服务器
        build = new BuildMailServer(promotionMail.mailServer);
        build.setReader(new ReadFromMap());
        build.build();

        //配置邮件
        build = new BuildMail(promotionMail.mailList, promotionMail.product);
        build.setReader(new ReadFromDatabase(promotionMail.product));
        build.build();
        //发送邮件
        promotionMail.sendEMails(emailDebug, promotionMail.mailList);


    }


    protected void sendEMails(boolean debug, List mailingList) throws IOException {

        System.out.println("开始发送邮件");


        if (mailingList != null) {
            Iterator iter = mailingList.iterator();
            while (iter.hasNext()) {
                Mail mail = (Mail) iter.next();
                try {
                    if (mail.getToAddress().length() > 0) {
						MailUtil.sendEmail(mail, mailServer, debug);

                    }
                } catch (Exception e) {

                    try {
						MailUtil.sendEmail(mail, mailServer, debug);

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
