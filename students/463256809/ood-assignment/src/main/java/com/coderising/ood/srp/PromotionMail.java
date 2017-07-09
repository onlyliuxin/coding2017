package com.coderising.ood.srp;

import java.io.File;

public class PromotionMail {

	public static void main(String[] args) throws Exception {

//		File f = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		File f = new File("/Users/wenwei/mygit/coding2017/students/463256809/ood-assignment/src/main/java/com/coderising/ood/srp/product_promotion.txt");
		boolean emailDebug = false;

		PromotionMail pe = new PromotionMail(f, emailDebug);

	}

	
	public PromotionMail(File file, boolean mailDebug) throws Exception {
		
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		FileUtil.readFile(file);

		MailUtil.sendEMails(mailDebug, MailUtil.loadMailingList());
	}


}
