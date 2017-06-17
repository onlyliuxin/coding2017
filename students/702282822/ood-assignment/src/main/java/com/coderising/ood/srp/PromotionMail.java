package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail extends Mail {			//inheritance from mail	
	

	public static void main(String[] args) throws Exception {

		File f = new File("\\product_promotion.txt");
		boolean emailDebug = false;

		PromotionMail pe = new PromotionMail(f, emailDebug);

	}
	
	public PromotionMail(File file, boolean mailDebug) throws Exception {				
		super(file, mailDebug);
	}
	
	
	
	protected void setSendMailQuery() throws Exception {		
		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + Product.getProductID() +"' "
				+ "and send_mail=1 ";
				
		System.out.println("loadQuery set");
	}

	protected void setMessage(String name) throws IOException 
	{				
		subject = "您关注的产品降价了";
		message = "尊敬的 "+ name +", 您关注的产品 " + Product.getProductDesc() + " 降价了，欢迎购买!" ;						
	}


	@Override
	protected void readFile(File file) throws IOException // @02C
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			Product.setProductID(data[0]); 
			Product.setProductDesc(data[1]); 
			
			System.out.println("产品ID = " + Product.getProductID() + "\n");
			System.out.println("产品描述 = " + Product.getProductDesc() + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}
		
		
		
	
	
	
	
	
	
	
	
}
