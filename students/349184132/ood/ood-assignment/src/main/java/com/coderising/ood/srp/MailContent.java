package com.coderising.ood.srp;

/**
 * Created by wang on 2017/6/17.
 */
public class MailContent {

    private Configuration config = new Configuration();

    private String smtpHost;

    private String altSmtpHost;

    private String fromAddress;

    private String subject;

    private String message;



    public String getSmtpHost() {
        return smtpHost;
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public void setSMTPHost() {
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);

    }

    public void setAltSMTPHost() {
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
    }


    public void setFromAddress() {
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }


    public void setMessage(String uName, String productDesc){


        subject = "您关注的产品降价了";
        message = "尊敬的 "+uName+", 您关注的产品 " + productDesc + " 降价了，欢迎购买!" ;

    }
}
