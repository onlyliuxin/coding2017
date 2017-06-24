package ood.srp;

/**
 * Created by jimmy on 6/20/2017.
 */
public class Mail {
    private MailAccount mailAccount;
    private String toAddress;
    private String subject;
    private String message;

    public Mail(MailAccount mailAccount, String toAddress, String subject, String message) {
        this.mailAccount = mailAccount;
        this.toAddress = toAddress;
        this.subject = subject;
        this.message = message;
    }

    public MailAccount getMailAccount() {
        return mailAccount;
    }

    public void setMailAccount(MailAccount mailAccount) {
        this.mailAccount = mailAccount;
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
}
