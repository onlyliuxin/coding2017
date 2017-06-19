package work01.srp;

public class MailBox {

    private String smtpHost = null;
    private String altSmtpHost = null;
    private String fromAddress = null;

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public void setAltSmtpHost(String altSmtpHost) {
        this.altSmtpHost = altSmtpHost;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void sendEmail(Mail mail, String smtpHost, boolean debug) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(mail.getToAddress()).append("\n");
        buffer.append("Subject:").append(mail.getSubject()).append("\n");
        buffer.append("Content:").append(mail.getMessage()).append("\n");
        buffer.append("SMTPHost:").append(smtpHost).append("\n");
        System.out.println(buffer.toString());
    }
}
