package com.coderising.ood.srp.optimize;

import java.util.List;

/**
 * 发邮件的行为类
 * Created by luoziyihao on 6/12/17.
 */
public class PromotionMailableBehavior {

    public void send(List<PromotionMailClaim> emailSendClaimList) {
        for (PromotionMailClaim promotionMailClaim : emailSendClaimList) {
            sendEmailForOneClaim(promotionMailClaim);
        }
    }

    private void sendEmailForOneClaim(PromotionMailClaim promotionMailClaim) {
        System.out.println("开始发送邮件");

        try {
            doSendMail(promotionMailClaim);
        } catch (Exception e) {
            try {
                promotionMailClaim.setSmtpHost(promotionMailClaim.getAltSmtpHost());
                doSendMail(promotionMailClaim);
            } catch (Exception e1) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e.getMessage());
            }
        }
    }

    private void doSendMail(PromotionMailClaim promotionMailClaim) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(promotionMailClaim.getFromAddress()).append("\n");
        buffer.append("To:").append(promotionMailClaim.getToAddress()).append("\n");
        buffer.append("Subject:").append(promotionMailClaim.getSubject()).append("\n");
        buffer.append("Content:").append(promotionMailClaim.getMessage()).append("\n");
        System.out.println(buffer.toString());
    }
}
