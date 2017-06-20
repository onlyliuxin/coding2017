package com.coderising.ood.srp.model;

import com.coderising.ood.srp.dao.DBUtil;

import java.util.List;

/**
 * Created by Iden on 2017/6/14.
 */
public class Product {
    String id;
    String description;

    public Product(String id, String descript) {
        this.id = id;
        this.description = descript;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getSubscribers() {
        List<User> userList = null;
        String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + id + "' "
                + "and send_mail=1 ";
        userList = DBUtil.query(sendMailQuery);
        return userList;

    }


}
