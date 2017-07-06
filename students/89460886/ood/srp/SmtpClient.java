package ood.srp;

import java.util.Map;

/**
 * @author jiaxun
 */
public class SmtpClient {

    private static volatile SmtpClient instance = null;

    private String smtpHost = null;
    private String altSmtpHost = null;
    private String fromAddress = null;

    private SmtpClient() {
        Configuration config = new Configuration();
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    public static SmtpClient sharedInstance() {
        if (instance == null) {
            synchronized (SmtpClient.class) {
                if (instance == null) {
                    return new SmtpClient();
                }
            }
        }
        return instance;
    }

    public void sendEmail(IRequest request, boolean debug) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        Map<String, String> params = request.getParams();
        // 这里为了演示方便，直接根据请求的 key 获取内容
        buffer.append("To:").append(params.get("toAddress")).append("\n");
        buffer.append("Subject:").append(params.get("subject")).append("\n");
        buffer.append("Content:").append(params.get("message")).append("\n");
        System.out.println(buffer.toString());
    }
}
