package com.coderising.ood.srp;

import java.util.HashMap;

/**
 * 邮件配置工具类
 * 
 * @author lin
 * @since
 */
public class ConfigurationUtil {
    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";

    public static void configure(PromotionMail mail, Configuration config) {
        mail.smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
        mail.altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
        mail.fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);

    }

    public static void configure2(PromotionMail mail, HashMap userInfo, Product product) {
        mail.toAddress = (String) userInfo.get(EMAIL_KEY);
        if (mail.toAddress.length() > 0) {
            String name = (String) userInfo.get(NAME_KEY);

            mail.subject = "您关注的产品降价了";
            mail.message = "尊敬的 " + name + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!";
        }
    }
}
