package ood.work.srp;

/**
 * Created by Administrator on 2017/6/17 0017.
 */
public class Email {

    private String toAddress;
    private String subject;
    private String message;

    public Email(String toAddress, String subject, String message) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.message = message;
    }

    public String getToAddress() {
        return toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
