package com.coderising.myood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by thomas_young on 20/6/2017.
 */
public class MailService {
    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";
    private static String fromAddress;
    private static EmailParam emailParam;

    static {
        emailParam = new EmailParam();
        fromAddress = emailParam.getFromAddress();
    }

    public void sendMails(boolean debug) throws Exception {
        ProductInfo productInfo = new ProductInfo();
        UserDao userDao = new UserDao();
        List userInfos = userDao.loadMailingList(productInfo.getProductID());
        List<MailInfo> mailInfos = convertToMails(userInfos, productInfo);
        System.out.println("开始发送邮件");
        for (MailInfo mail: mailInfos) {
            sendOneMail(mail, debug);
        }

    }

    private void sendOneMail(MailInfo mail, boolean debug) {
        try {
            MailUtil.sendEmail(
                    mail.getToAddress(),
                    fromAddress,
                    mail.getSubject(),
                    mail.getMessage(),
                    emailParam.getSmtpHost(), debug);
        } catch (Exception e) {

            try {
                MailUtil.sendEmail(mail.getToAddress(),
                        fromAddress,
                        mail.getSubject(),
                        mail.getMessage(),
                        emailParam.getAltSmtpHost(),
                        debug);
            } catch (Exception e2)
            {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
            }
        }
    }

    public static class MailInfo {

        public String getFromAddress() {
            return fromAddress;
        }

        public String getToAddress() {
            return toAddress;
        }

        public String getSubject() {
            return subject;
        }

        public String getMessage() {
            return message;
        }

        private String fromAddress = null;
        private String toAddress = null;
        private String subject = null;
        private String message = null;


        public MailInfo(String fromAddress, String toAddress, String subject, String message) {
            this.fromAddress = fromAddress;
            this.toAddress = toAddress;
            this.subject = subject;
            this.message = message;
        }
    }

    private List<MailInfo> convertToMails(List userInfos, ProductInfo productInfo) throws IOException {
        List<MailInfo> mailInfos = new LinkedList<>();
        if (userInfos != null) {
            Iterator iter = userInfos.iterator();
            while (iter.hasNext()) {
                MailInfo mailInfo = configureEMail((HashMap) iter.next(), productInfo);
                if (mailInfo != null) {
                    mailInfos.add(mailInfo);
                }
            }
        }
        return mailInfos;
    }

    private MailInfo configureEMail(HashMap userInfo, ProductInfo productInfo) throws IOException
    {
        String toAddress = (String) userInfo.get(EMAIL_KEY);
        if (toAddress.length() > 0) {
            String name = (String) userInfo.get(NAME_KEY);
            String subject = "您关注的产品降价了";
            String message = "尊敬的 "+name+", 您关注的产品 " + productInfo.getProductDesc() + " 降价了，欢迎购买!" ;
            return new MailInfo(fromAddress, toAddress, subject, message);
        }
        return null;
    }
}
