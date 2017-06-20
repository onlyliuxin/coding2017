package ood.work.srp;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17 0017.
 *
 */
public class SMTPClient {

    private String smtpHost;
    private String altSmtpHost;
    private String emailAdmin;

    public void config() {
        Configuration configuration = new Configuration();
        this.smtpHost = configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
        this.altSmtpHost = configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
        this.emailAdmin = configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    public SMTPClient() {
        config();
    }

    /**
     * 发送一封邮件
     * @param debug
     * @param mailInfo
     */
    public void sendEmail(boolean debug, Email mailInfo) {
        try
        {
            if (mailInfo.getToAddress().length() > 0)
                MailUtil.sendEmail(mailInfo, emailAdmin, smtpHost, debug);
        }
        catch (Exception e)
        {

            try {
                MailUtil.sendEmail(mailInfo, emailAdmin, altSmtpHost, debug);

            } catch (Exception e2)
            {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
            }
        }
    }



    /**
     * 发送多封邮件
     */

    public void sendEmails(boolean debug, List<Email> mailingList){
        if (mailingList != null) {
            Iterator<Email> iter = mailingList.iterator();
            while (iter.hasNext()) {
                sendEmail(debug, iter.next());
            }
        }
        else {
            System.out.println("没有邮件发送");
        }
    }



}
