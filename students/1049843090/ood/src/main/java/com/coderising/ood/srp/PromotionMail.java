package com.coderising.ood.srp;

import java.util.List;

public class PromotionMail {


    public static void main(String[] args) throws Exception {
        new PromotionMail().sendEMails();

    }


    private void setMessage(ProductInfo productInfo, UserInfo userInfo, MailData mailData) {


        mailData.setSubject("您关注的产品降价了");
        String message = "尊敬的 " + userInfo.getName() + ", 您关注的产品 " + productInfo.getDescription() + " 降价了，欢迎购买!";
        mailData.setMessage(message);

    }


    private void sendEMails() throws Exception {
        ProductInfoService productInfoService = new ProductInfoService();
        UserInfoService userInfoService = new UserInfoService();
        List<ProductInfo> productInfos = productInfoService.getPromotionProducts();

        System.out.println("开始发送邮件");
        MailData mailData = getMailData();
        productInfos.forEach(e -> {
            List<UserInfo> userInfos = userInfoService.getuserInfoByProduceId(e.getId());
            userInfos.forEach(userInfo -> {
                if (userInfo.getMail().length() > 0) {
                    mailData.setToAddress(userInfo.getMail());
                    setMessage(e, userInfo, mailData);
                    MailUtil.sendEmail(mailData);
                } else {
                    System.out.println(userInfo.getName() + "邮件格式不正确");
                }

            });
        });


    }


    private MailData getMailData() {
        Configuration config = new Configuration();
        MailData mailData = new MailData();
        mailData.setSmtpHost(config.getProperty(ConfigurationKeys.SMTP_SERVER));
        mailData.setAltSmtpHost(config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
        mailData.setFromAddress(config.getProperty(ConfigurationKeys.EMAIL_ADMIN));
        return mailData;
    }

}