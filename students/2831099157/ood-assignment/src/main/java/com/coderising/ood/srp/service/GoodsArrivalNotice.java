package com.coderising.ood.srp.service;

/**
 * Created by Iden on 2017/6/14.
 * 到货通知
 */
public class GoodsArrivalNotice extends Notice {
    public GoodsArrivalNotice() {
        getProductsFunction = new GetProductsFromFile();
        sendMailFunction = new SendGoodsArrivalMail();
    }

}
