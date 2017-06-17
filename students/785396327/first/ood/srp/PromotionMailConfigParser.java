package first.ood.srp;

/**
 * Created by william on 2017/6/14.
 */
public class PromotionMailConfigParser extends ConfigParser {

    @Override
    void parseInfoFromConfig(Email email) {
        Configuration configuration = new Configuration();
        email.setSMTPHost(configuration.getProperty(ConfigurationKeys.SMTP_SERVER));
        email.setFromAddress(configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN));
        email.setAltSMTPHost(configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
    }
}
