package com.coderising.ood.srp;

/**
 * Created by Mori on 2017/6/15.
 */
public class Mail {
    private String subject;
    private String message;
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
    protected void setPromotionMessage(String userName, String productDesc) {
        this.setSubject("您关注的产品降价了");
        this.setMessage("尊敬的 " + userName + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!");
    }
}
