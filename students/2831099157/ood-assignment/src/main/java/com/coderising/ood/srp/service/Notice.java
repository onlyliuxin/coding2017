package com.coderising.ood.srp.service;

import com.coderising.ood.srp.interfaces.GetProductsFunction;
import com.coderising.ood.srp.interfaces.SendMailFunction;
import com.coderising.ood.srp.model.Product;

import java.util.List;

/**
 * Created by Iden on 2017/6/14.
 * 各类通知（降价促销，抢购活动，到货通知等等）
 */
public abstract class Notice {
    GetProductsFunction getProductsFunction;
    SendMailFunction sendMailFunction;

    public List<Product> getProducts() {
        return getProductsFunction.getProducts();
    }

    public void sendMail(List<Product> products) {
        sendMailFunction.sendMail(products);
    }

}
