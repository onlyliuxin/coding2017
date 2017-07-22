package com.coderising.ood.srp;

import java.io.File;

public class ProductService {
    public Product getPromotionProduct() {
	File f = new File("F:\\coding2017\\com\\codering\\ood\\src\\product_promotion.txt");
	Product product = readFile(f);
	return product;
    }

    private Product readFile(File file) {

	return null;
    }
}
