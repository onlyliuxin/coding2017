package ood.srp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiaxun
 */
public class MailRequest implements IRequest {

    private String toAddress;
    private String subject;
    private String message;

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> map = new HashMap<>();
        map.put("toAddress", toAddress);
        map.put("subject", subject);
        map.put("message", message);
        return map;
    }

    @Override
    public String getUrl() {
        return null;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
