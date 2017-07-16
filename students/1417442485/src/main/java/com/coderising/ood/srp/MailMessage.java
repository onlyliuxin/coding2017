package main.java.com.coderising.ood.srp;

public interface MailMessage {

    String getFromAddress();
    String getToAddress();
    String getSubject();
    String getMessageBody();
}
