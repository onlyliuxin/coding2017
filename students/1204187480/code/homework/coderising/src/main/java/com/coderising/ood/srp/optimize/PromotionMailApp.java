package com.coderising.ood.srp.optimize;

import java.util.List;

/**
 * Created by luoziyihao on 6/12/17.
 */
public class PromotionMailApp {

    public static void main(String args[]){
        ProductParser productParser = new ProductParser();
        productParser.setProductClassPath("product_promotion.txt");
        List<Product> products = productParser.parse();
    }
}
