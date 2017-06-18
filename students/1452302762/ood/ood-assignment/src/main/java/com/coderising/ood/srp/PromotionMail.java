package com.coderising.ood.srp;

import java.io.File;

public class PromotionMail {

	public static void main(String[] args) throws Exception {
		File f = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		boolean emailDebug = false;
		Product product=new Product(FileUtil.readFile(f));
		MailUtil.sendEMails(emailDebug,product.getProductID()); 
	}
}
