package com.coderising.ood.srp;

import com.coderising.ood.srp.bean.ProductInfo;
import com.coderising.ood.srp.bean.UserInfo;
import com.coderising.ood.srp.util.MailUtil;

import java.util.List;

/**
 * Created by wang on 2017/6/17.
 */

/**
 * 发送邮件类
 */
public class PromotionMail {

    private List<ProductInfo> proInfo;
    private List<UserInfo> userInfo;
    private MailContent mailContent;

    public PromotionMail(){

    }



    public PromotionMail(List<ProductInfo> proInfo, List<UserInfo> userInfo, MailContent mailContent) {
        this.proInfo = proInfo;
        this.userInfo = userInfo;
        this.mailContent = mailContent;
    }

    public void sendEMail(){

        sendEMail(false);

    }

    public void sendEMail(boolean debug){

        String productDesc = proInfo.get(0).getProductDesc();

        System.out.println("开始发送邮件：");
        for(UserInfo u : userInfo) {

            String name = u.getName();

            setEmailContent(productDesc, name);

            String toAddress = u.getEmail();
            String fromAddress = mailContent.getFromAddress();
            String subject = mailContent.getSubject();
            String message = mailContent.getMessage();
            String smtpHost = mailContent.getSmtpHost();
            String altSmtpHost = mailContent.getAltSmtpHost();

            if(message == "" && message == null){
                return ;
            }
            MailUtil.sendEmail(toAddress, fromAddress, subject, message, smtpHost, altSmtpHost, debug);

        }
        System.out.println("邮件发送完毕！");
    }

    private void setEmailContent(String productDesc, String name) {
        mailContent.setFromAddress();
        mailContent.setSMTPHost();
        mailContent.setAltSMTPHost();
        mailContent.setMessage(name,productDesc);
    }

}
