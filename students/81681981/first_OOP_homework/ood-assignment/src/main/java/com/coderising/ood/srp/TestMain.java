package com.coderising.ood.srp;

import java.io.File;

public class TestMain {

	public static void main(String[] args) throws Exception {

		File f = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		boolean emailDebug = false;

		PromotionMail pe = new PromotionMail(f, emailDebug);
		

	}
}
