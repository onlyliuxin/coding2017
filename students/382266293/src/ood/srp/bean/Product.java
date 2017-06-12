package ood.srp.bean;

import java.util.List;

/**
 * Created by onlyLYJ on 2017/6/12.
 */
public class Product {

    private String productID;
    private String productDesc;
    private List<Subscriber> subscribers;

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

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

}
