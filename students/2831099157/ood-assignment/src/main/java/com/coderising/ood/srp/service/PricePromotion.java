package com.coderising.ood.srp.service;

/**
 * Created by Iden on 2017/6/14.
 */
public class PricePromotion extends Notice {
    public PricePromotion() {
        getProductsFunction = new GetProductsFromFile();
        sendMailFunction = new SendPriceMail();
    }

}
