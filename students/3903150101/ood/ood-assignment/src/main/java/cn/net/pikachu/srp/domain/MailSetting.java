package cn.net.pikachu.srp.domain;

/**
 * 
 */
public class MailSetting {

    /**
     * Default constructor
     */
    public MailSetting() {
    }

    /**
     * smtp主机地址
     */
    private String smtpHost;

    /**
     * 备用smtp主机地址(可选)
     */
    private String altSmtpHost;

    /**
     * 发送方地址
     */
    private String fromAddress;

    /**
     * 接收方地址
     */
    private String toAddress;

    /**
     * 邮件主题
     */
    private String subject;

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }

    public void setAltSmtpHost(String altSmtpHost) {
        this.altSmtpHost = altSmtpHost;
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
}