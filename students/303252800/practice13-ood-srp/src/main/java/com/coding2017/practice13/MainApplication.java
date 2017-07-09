package com.coding2017.practice13;

import java.util.List;

public class MainApplication {

    public static void main(String[] args) throws Exception {
        List<PromotionProduct> products = PromotionProduct.readFromFile("com/coderising/ood/srp/product_promotion.txt");

        for (PromotionProduct product : products) {
            List<PromotionSubscriber> subscribers = PromotionSubscriber.querySubscribers(product.getProductId());
            for (PromotionSubscriber subscriber : subscribers) {
                PromotionNotifier.sendEmail(product, subscriber);
            }
        }
    }
}
