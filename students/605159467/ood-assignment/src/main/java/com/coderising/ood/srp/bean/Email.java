package com.coderising.ood.srp.bean;

import com.coderising.ood.srp.resource.ConfigurationKeys;
import com.coderising.ood.srp.utils.MailUtil;

/**
 * Created with IDEA
 * Created by fuyi.ren on 2017/6/17  22:04
 * Description:
 */
public class Email
{






    private String toAddress;
    private String fromAddress;
    private String subject;
    private String message;
    private String smtpHost;

    public String getFromAddress()
    {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress)
    {
        this.fromAddress = fromAddress;
    }
    public String getToAddress()
    {
        return toAddress;
    }

    public void setToAddress(String toAddress)
    {
        this.toAddress = toAddress;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getMessage()
    {
         return  message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getSmtpHost()
    {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost)
    {
        this.smtpHost = smtpHost;
    }

    /**
     *  具有发送的行为
     */
    public  void sendMessage(){
        MailUtil.sendEmail(this.toAddress,
                ConfigurationKeys.EMAIL_ADMIN,
                this.subject,
                this.message,
                ConfigurationKeys.ALT_SMTP_SERVER,
                true);
    }

    /**
     *  备用发送
     */
    public  void  standbySendMessage(){
        MailUtil.sendEmail(this.toAddress,
                ConfigurationKeys.EMAIL_ADMIN,
                this.subject,
                this.message,
                ConfigurationKeys.SMTP_SERVER,
                true);
    }

}
