package com.coderising.ood.srp.optimize;

import java.util.List;

/**
 * main 函数启动类
 * Created by luoziyihao on 6/12/17.
 */
public class PromotionMailApp {

    public static void main(String args[]) {
        List<Product> products = new ProductParser().parse("product_promotion.txt");

        List<User> users = new UserService().loadMailingList();

        List<PromotionMailClaim> promotionMailClaims = new PromotionMailClaim()
                .load(products, users, new SmptPropeties(), true);

        new PromotionMailableBehavior().send(promotionMailClaims);

    }

}
