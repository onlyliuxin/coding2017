package com.coderising.ood.srp;

/**
 * Created by 360682644 on 2017/6/13.
 */
public class Product implements IMailable {
    private String productID = null;
    private String productDesc = null;

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
    public String toEmailText(String... params) {
        return "尊敬的 "+ params[0] +", 您关注的产品 " + productDesc + " 降价了，欢迎购买!" ;
    }
    public String toEmailSubject(String... params) {
        return "您关注的产品降价了";
    }
}
