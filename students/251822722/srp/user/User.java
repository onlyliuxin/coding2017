package com.coderising.ood.srp.user;

import com.coderising.ood.srp.mail.Mail;
import com.coderising.ood.srp.mail.PromotionMail;
import com.coderising.ood.srp.product.Product;


/**
 * com.coderising.ood.srp.user
 * Created by Eric Wang on 6/19/17.
 */
public class User {

    Mail mail = new PromotionMail();

    String userName ;

    String userEmail;

    public void sendPriceChangeEmail(Product product) {

        mail.sendPriceChangeEmail(this, product);

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
