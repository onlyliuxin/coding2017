package com.coderising.ood.srp;

import com.coderising.ood.srp.service.GoodsArrivalNotice;
import com.coderising.ood.srp.service.Notice;

/**
 * 可以根据不同运营方案，添加GetProductsFunction，SendMailFunction接口实现类及Notice子类，向订阅者发送通告
 */
public class PromotionMail {

    public static void main(String[] args) throws Exception {
        //降价促销
//      Notice notice = new PricePromotion();
        //到货通知
        Notice notice = new GoodsArrivalNotice();
        notice.sendMail(notice.getProducts());

    }


}
