package com.github.orajavac.coding2017.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {


	protected String sendMailQuery = null;


	protected Mail mail = new Mail();

	

	private static Configuration config; 
	
	
	
	
	

	public static void main(String[] args) throws Exception {

		File f = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		boolean emailDebug = false;

		PromotionMail pe = new PromotionMail(f, emailDebug);

	}

	
	public PromotionMail(File file, boolean mailDebug) throws Exception {
		
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		Product p = FileUtil.readFile(file);

		
		config = new Configuration();
		
		mail.setSmtpHost(MailUtil.setSMTPHost(config));
		mail.setAltSmtpHost(MailUtil.setAltSMTPHost(config)); 
	

		mail.setFromAddress(MailUtil.setFromAddress(config));

		
		DBUtil.setLoadQuery(p.getProductID());
		
		MailUtil.sendEMails(mailDebug, loadMailingList(),p,mail); 

		
	}

	


	protected List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}
}
