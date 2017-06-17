package first.ood.srp;

/**
 * Created by william on 2017/6/12.
 */
public class Email {
    protected String smtpHost;
    protected String altSmtpHost;
    protected String fromAddress;
    protected String toAddress;
    protected String subject;
    protected String message;

    protected void setSMTPHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    protected void setAltSMTPHost(String altSmtpHost) {
        this.altSmtpHost = altSmtpHost;

    }

    protected void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    protected void setMessage(String message) {
        this.message = message;
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
}
