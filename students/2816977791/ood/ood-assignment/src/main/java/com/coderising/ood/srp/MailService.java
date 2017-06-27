package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 发送邮件
 *
 * @author nvarchar
 *         date 2017/6/26
 */
public class MailService {
    protected static void sendEMails(boolean debug, List mailingList, PromotionMail promotionMail) throws IOException {

        System.out.println("开始发送邮件");

        if (mailingList != null) {
            Iterator iter = mailingList.iterator();
            while (iter.hasNext()) {
                promotionMail.configureEMail((HashMap) iter.next());
                try {
                    if (promotionMail.hasValidToAddress())
                        MailUtil.sendEmail(promotionMail, debug);
                } catch (Exception e) {

                    try {
                        MailUtil.sendEmail(promotionMail, debug);

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
