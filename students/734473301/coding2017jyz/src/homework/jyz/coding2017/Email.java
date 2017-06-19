package homework.jyz.coding2017;

/**
 * 邮件实体信息
 * Created by jyz on 2017/6/13.
 */
public class Email {
    private String fromAddress;
    private String toAddress;
    private String subject;
    private String message;

    public Email() {
    }

    public Email(String fromAddress, String toAddress, String subject, String message) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.subject = subject;
        this.message = message;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
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
