package com.coderising.ood.srp;

import java.util.List;

public class PromotionMail {
	
	protected String sendMailQuery = null;
	private Product product = null;
	
	public static void main(String[] args) throws Exception {
		
		boolean emailDebug = false;
		PromotionMail pe = new PromotionMail( emailDebug );
		
	}
	
	public PromotionMail( boolean mailDebug ) throws Exception {
		
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		product = FileUtils.readFile();
		
		setLoadQuery();
		
		MailUtil.sendEMails(mailDebug, product ,loadMailingList()); 
		
	}
	
	protected void setLoadQuery() throws Exception {
		
		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + product.getProductID() +"' "
				+ "and send_mail=1 ";
		System.out.println("loadQuery set");
	}
	
	protected List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}

}
