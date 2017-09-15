package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nengneng on 2017/6/19.
 */
public class Mail {

    private String fromAddress;
    private String toAddress;
    private String subject;
    private String message;


    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";

    public Mail(){

    }

    public Mail(String fromAddress, String toAddress, String subject, String message) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.subject = subject;
        this.message = message;
    }

    public static String getNameKey() {
        return NAME_KEY;
    }

    public static String getEmailKey() {
        return EMAIL_KEY;
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

    private void setMessage(HashMap userInfo, Product product) throws IOException
    {
        String name = (String) userInfo.get(NAME_KEY);
        this.subject = "您关注的产品降价了";
        this.message = "尊敬的 "+name+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;
    }








}
