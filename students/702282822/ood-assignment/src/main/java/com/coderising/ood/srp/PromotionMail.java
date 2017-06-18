package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;

public class PromotionMail extends Mail {			//inheritance from mail	
	
	

	public static void main(String[] args) throws Exception {

		File f = new File("./product_promotion.txt");		
		boolean emailDebug = false;
		PromotionMail pe = new PromotionMail(f, emailDebug);
		
	}
	
	public PromotionMail(File file, boolean mailDebug) throws Exception 
	{				
		super(file, mailDebug);		
	}
	
	protected void setSendMailQuery(Theme theme) throws Exception {		
		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + theme.getID() +"' "
				+ "and send_mail=1 ";
				
		System.out.println("loadQuery set");
	}
	

	protected void setMessage(String name, Theme theme) throws IOException 
	{				
		subject = "您关注的产品降价了";
		message = "尊敬的 "+ name +", 您关注的产品 " + theme.getDesc() + " 降价了，欢迎购买!" ;						
	}
}
