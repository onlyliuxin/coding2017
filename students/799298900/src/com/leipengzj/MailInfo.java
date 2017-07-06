package com.leipengzj;

/**
 * Created by pl on 2017/6/19.
 */
public class MailInfo {

    protected String sendMailQuery = null;


    protected String smtpHost = null;
    protected String altSmtpHost = null;
    protected String fromAddress = null;
    protected String toAddress = null;
    protected String subject = null;
    protected String message = null;

    protected String productID = null;
    protected String productDesc = null;

    public String getSendMailQuery() {
        return sendMailQuery;
    }

    public void setSendMailQuery(String sendMailQuery) {
        this.sendMailQuery = sendMailQuery;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
