package com.coderising.ood.srp.server;

import com.coderising.ood.srp.MailUtil;
import com.coderising.ood.srp.mail.PromotionMail;
import com.coderising.ood.srp.setting.SystemSetting;
import com.coderising.ood.srp.setting.config.ConfigurationKeys;

/**
 * com.coderising.ood.srp
 * Created by Eric Wang on 6/19/17.
 */
public class SmtpMailServer {


    private static final String smtpHost = SystemSetting.getConfig().getProperty(ConfigurationKeys.SMTP_SERVER);
    ;
    private static final  String altSmtpHost = SystemSetting.getConfig().getProperty(ConfigurationKeys.ALT_SMTP_SERVER);


    public void sendEmail(PromotionMail promotionMail) {


        System.out.println("开始发送邮件");

        try {
            MailUtil.sendEmail(promotionMail.getTo(), promotionMail.getForm(), promotionMail.getSubject(), promotionMail.getMessage(), smtpHost);
        } catch (Exception e) {

            try {
                MailUtil.sendEmail(promotionMail.getTo(), promotionMail.getForm(), promotionMail.getSubject(), promotionMail.getMessage(), altSmtpHost);

            } catch (Exception e2) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
            }
        }
    }


}



