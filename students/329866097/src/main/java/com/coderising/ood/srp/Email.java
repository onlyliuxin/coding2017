package com.coderising.ood.srp;

/**
 * Created by tianxianhu on 2017/6/18.
 */
public class Email {

    private static String smtpHost;
    private static String altSmtpHost;
    private static String fromAddress;

    private static Configuration config;

    static  {
        config = new Configuration();
        setDefaultConfig(config);
    }

    private static void setDefaultConfig(Configuration config) {
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    public void send(User user, String product, String host) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();

        if(user.getEmail().length() > 0) {
            String name = user.getName();
            String subject = "您关注的产品降价了";
            String message = "尊敬的 " + name + ", 您关注的产品 " + product + " 降价了，欢迎购买!";

            buffer.append("From:").append(fromAddress).append("\n");
            buffer.append("To:").append(user.getEmail()).append("\n");
            buffer.append("Subject:").append(subject).append("\n");
            buffer.append("Content:").append(message).append("\n");
            System.out.println(buffer.toString());
        }
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }
}
