package com.mimieye.odd.srp.controller;

import com.mimieye.odd.srp.config.Configuration;
import com.mimieye.odd.srp.config.ConfigurationKeys;
import com.mimieye.odd.srp.dao.impl.UserInfoDAOImpl;
import com.mimieye.odd.srp.service.PromotionInfoService;
import com.mimieye.odd.srp.service.UserInfoService;
import com.mimieye.odd.srp.service.impl.PromotionInfoServiceImpl;
import com.mimieye.odd.srp.service.impl.UserInfoServiceImpl;
import com.mimieye.odd.srp.util.MailUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class PromotionAbstractMail {

    protected static Configuration config;
    protected static final String NAME_KEY = "NAME";
    protected static final String EMAIL_KEY = "EMAIL";
    protected String smtpHost = null;
    protected String altSmtpHost = null;
    protected String fromAddress = null;
    protected String toAddress = null;
    protected String subject = null;
    protected String message = null;
    protected String filePath;
    protected boolean mailDebug;
    protected List userInfos;
    protected String productID;
    protected String productDesc;

    public void init() throws Exception{
        config = new Configuration();
        setFilePath(config.getProperty(ConfigurationKeys.PROMOTION_FILEPATH));
        setMailDebug(Boolean.parseBoolean(ConfigurationKeys.EMAIL_DEBUG));
        setSMTPHost();
        setAltSMTPHost();
        setFromAddress();
        // 降价商品service
        PromotionInfoService promotionInfoService = new PromotionInfoServiceImpl();
        // 邮件接收人service
        UserInfoService userInfoService = new UserInfoServiceImpl(new UserInfoDAOImpl());
        // 获取第一条降价商品
        Map<String,String> promotion = promotionInfoService.listPromotions(filePath).get(0);
        setProductID(promotion.get("productID"));
        setProductDesc(promotion.get("productDesc"));
        // 获取邮件接收人
        setUserInfos(userInfoService.loadMailingList(productID));
    }


    protected void configureEMail(HashMap userInfo) throws IOException {
        toAddress = (String) userInfo.get(EMAIL_KEY);
        if (toAddress.length() > 0)
            setMessage(userInfo);
    }

    protected void sendEMails(boolean debug, List mailingList) throws IOException {

        System.out.println("开始发送邮件");

        if (mailingList != null) {
            Iterator iter = mailingList.iterator();
            while (iter.hasNext()) {
                configureEMail((HashMap) iter.next());
                try {
                    if (toAddress.length() > 0)
                        MailUtil.sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);
                } catch (Exception e) {

                    try {
                        MailUtil.sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug);

                    } catch (Exception e2) {
                        System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                    }
                }
            }

        } else {
            System.out.println("没有邮件发送");
        }

    }

    public void send() throws IOException { sendEMails(mailDebug, userInfos); }

    protected void setSMTPHost() { smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER); }

    protected void setAltSMTPHost() { altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER); }

    protected void setFromAddress() { fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN); }

    protected void setFilePath(String filePath) { this.filePath = filePath; }

    protected void setMailDebug(boolean mailDebug) { this.mailDebug = mailDebug; }

    protected void setUserInfos(List userInfos) { this.userInfos = userInfos; }

    protected void setProductID(String productID) { this.productID = productID; }

    protected void setProductDesc(String productDesc) { this.productDesc = productDesc; }

    protected abstract void setMessage(HashMap userInfo) throws IOException ;
}
