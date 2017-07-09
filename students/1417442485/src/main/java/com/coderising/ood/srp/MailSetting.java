package main.java.com.coderising.ood.srp;

public class MailSetting {
    static String smtpHost = Configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
    static String altSmtpHost = Configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
    final boolean emailDebug;

    public MailSetting(boolean emailDebug) {
        this.emailDebug = emailDebug;
    }
}
