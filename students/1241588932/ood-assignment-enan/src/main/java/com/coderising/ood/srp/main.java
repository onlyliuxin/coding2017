package com.coderising.ood.srp;

import com.coderising.ood.srp.service.IPromotionMail;
import com.coderising.ood.srp.service.impl.PromotionMailImpl;
import com.coderising.ood.srp.service.impl.ReadProductConfigImpl;
import com.coderising.ood.srp.service.impl.UserServiceImpl;

import java.io.File;
import java.net.URL;

/**
 * Created by Enan on 17/6/18.
 */
public class main {

    public static void main(String[] args) {
        IPromotionMail promotionMail = new PromotionMailImpl(new UserServiceImpl(), new ReadProductConfigImpl());
        URL base = Thread.currentThread().getContextClassLoader().getResource("");
        promotionMail.send(new File(base.getFile(), "product_promotion.txt"));
    }
}
