package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dell on 2017/6/15.
 */
public class Mail {

    protected String smtpHost = null;
    protected String altSmtpHost = null;
    protected String fromAddress = null;
    protected String toAddress = null;
    protected String subject = null;
    protected String message = null;



    protected String sendMailQuery = null;


    protected static final String NAME_KEY = "NAME";
    protected static final String EMAIL_KEY = "EMAIL";

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }

    public void setAltSmtpHost(String altSmtpHost) {
        this.altSmtpHost = altSmtpHost;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
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



    protected void sendEMails(boolean debug, List mailingList) throws IOException
    {

        System.out.println("开始发送邮件");


        if (mailingList != null) {
            Iterator iter = mailingList.iterator();
            while (iter.hasNext()) {
                configureEMail((HashMap) iter.next());
                try
                {
                    if (toAddress.length() > 0)
                        MailUtil.sendEmail(this, debug);
                }
                catch (Exception e)
                {

                    try {
                        MailUtil.sendEmail(this, debug);

                    } catch (Exception e2)
                    {
                        System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                    }
                }
            }


        }

        else {
            System.out.println("没有邮件发送");

        }

    }

    protected void configureEMail(HashMap userInfo) throws IOException
    {
    }

    protected List loadMailingList() throws Exception {
        return DBUtil.query(this.sendMailQuery);
    }

}
