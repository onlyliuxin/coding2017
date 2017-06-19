package com.coderising.ood.srp.dto;

import java.io.IOException;

/**
 * Created by guodongchow on 2017/6/15.
 */
public class Mail {
    protected String toAddress = null;
    protected String subject = null;
    protected String message = null;

    protected void setMessage(User userInfo,Product product) throws IOException
    {

        String name = userInfo.getName();

        subject = "您关注的产品降价了";
        message = "尊敬的 "+name+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;
        
    }

    public Mail(User userInfo,Product product){
        try {
            setMessage(userInfo,product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        toAddress = userInfo.getMailAddress();
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
}
