package com.coderising.ood.srp.mail;

import com.coderising.ood.srp.product.Product;
import com.coderising.ood.srp.user.User;


/**
 * com.coderising.ood.srp
 * Created by Eric Wang on 6/19/17.
 */
public interface Mail {


    void sendPriceChangeEmail(User user, Product product);
}
