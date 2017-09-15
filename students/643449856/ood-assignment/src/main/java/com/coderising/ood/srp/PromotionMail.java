package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {
	private static Configuration config; 
	
	
	

	

	public static void main(String[] args) throws Exception {

		File f = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		boolean emailDebug = false;

		PromotionMail pe = new PromotionMail(f, emailDebug);

	}

	
	public PromotionMail(File file, Product product,boolean mailDebug) throws Exception {
		
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		FileUtil.readFile(file, product);
		config = new Configuration();
		DBUtil.setLoadQuery(product.getProductID());
		sendEMails(mailDebug, DBUtil.loadMailingList());

		
	}













	







}
