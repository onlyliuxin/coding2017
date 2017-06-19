package com.coderising.ood.srp.optimize;

import java.util.ArrayList;
import java.util.List;

/**
 * 发发送邮件的必要参数 vo
 * Created by luoziyihao on 6/12/17.
 */
public class PromotionMailClaim {
    private String toAddress;
    private String fromAddress;
    private String subject;
    private String message;
    private String smtpHost;
    private String altSmtpHost;
    private Boolean mailDebug;

    private PromotionMailClaim init(Product product, User user, SmptPropeties smptPropeties, Boolean mailDebug) {
        this.toAddress = user.getEmail();
        this.fromAddress = smptPropeties.getFromAddress();
        this.subject = "您关注的产品降价了";
        this.message = "尊敬的 " + user.getName() + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!";
        this.smtpHost = smptPropeties.getSmtpHost();
        this.altSmtpHost = smptPropeties.getAltSmtpHost();
        this.mailDebug = mailDebug;
        return this;
    }

    public List<PromotionMailClaim> load(List<Product> products, List<User> users, SmptPropeties smptPropeties
            , boolean mailDebug) {
        List<PromotionMailClaim> promotionMailClaims = new ArrayList<>();
        for (Product product : products) {
            for (User user : users) {
                PromotionMailClaim promotionMailClaim = new PromotionMailClaim()
                        .init(product, user, smptPropeties, mailDebug);
                promotionMailClaims.add(promotionMailClaim);
            }
        }
        return promotionMailClaims;
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }

    public void setAltSmtpHost(String altSmtpHost) {
        this.altSmtpHost = altSmtpHost;
    }

    public Boolean getMailDebug() {
        return mailDebug;
    }

    public void setMailDebug(Boolean mailDebug) {
        this.mailDebug = mailDebug;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

}
