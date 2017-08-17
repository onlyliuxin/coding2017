package main.java.com.coderising.ood.srp;

public class PromotionMailMessage implements MailMessage {
    private final static String FROM_ADDRESS = Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    private final static String SUBJECT = "您关注的产品降价了";
    private final String toAddress;
    private final String message;

    public PromotionMailMessage(final String toAddress, final String userName, final String productDesc){
        this.toAddress = toAddress;
        if (toAddress.length() > 0) {
            message = "尊敬的 "+userName+", 您关注的产品 " + productDesc + " 降价了，欢迎购买!" ;
        } else {
            message = null;
        }
    }

    @Override
    public String getFromAddress() {
        return FROM_ADDRESS;
    }

    @Override
    public String getToAddress() {
        return toAddress;
    }

    @Override
    public String getSubject() {
        return SUBJECT;
    }

    @Override
    public String getMessageBody() {
        return message;
    }
}
