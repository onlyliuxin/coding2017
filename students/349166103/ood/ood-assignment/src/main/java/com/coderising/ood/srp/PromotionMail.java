package com.coderising.ood.srp;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {

	public static void main(String[] args) throws Exception {
		File f = new File(System.getProperty("user.dir")+"/src/main/java/com/coderising/ood/srp/product_promotion.txt");
		boolean emailDebug = false;
		PromotionMail pe = new PromotionMail(emailDebug, f);
	}
		
	public PromotionMail(boolean debug, File f) throws Exception 
	{
		Configuration config = new Configuration(); 
		DBUtil dbu = new DBUtil(f);
		List mailingList = dbu.loadMailingList();
		System.out.println("开始发送邮件");
		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) { 
				MailUtil mu = new MailUtil(config, f, (HashMap)iter.next(), dbu.getProductDesc());
				mu.sendEmail(debug);
			}
		}
		else {
			System.out.println("没有邮件发送");
		}
	}
}
