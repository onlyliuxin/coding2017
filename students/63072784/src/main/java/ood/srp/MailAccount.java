package ood.srp;

/**
 * Created by jimmy on 6/20/2017.
 */
public class MailAccount {
    private String smtpHost;
    private String altSmtpHost;
    private int port;
    private String account;
    private String password;

    public static MailAccount buildAccount(Configuration config) {
        MailAccount mailAccount = new MailAccount();
        mailAccount.smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
        mailAccount.altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
        mailAccount.port = Integer.parseInt(config.getProperty(ConfigurationKeys.EMAIL_PORT));
        mailAccount.account = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
        mailAccount.password = config.getProperty(ConfigurationKeys.EMAIL_ADMIN_PASSWORD);
        return mailAccount;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }

    public int getPort() {
        return port;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
