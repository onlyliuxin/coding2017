package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PromotionMail extends Mail {			//inheritance from mail	
	
	private LinkedList<Product> products;

	public static void main(String[] args) throws Exception {

		File f = new File("./product_promotion.txt");		
		boolean emailDebug = false;
		PromotionMail pe = new PromotionMail(f, emailDebug);
		
	}
	
	public PromotionMail(File file, boolean mailDebug) throws Exception 
	{				
		super(file);
		products = new LinkedList<>();
		sendEMails(mailDebug);
	}
	
	
	
	protected void setSendMailQuery(Product pro) throws Exception {		
		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + pro.getProductID() +"' "
				+ "and send_mail=1 ";
				
		System.out.println("loadQuery set");
	}

	protected void setMessage(String name, Product pro) throws IOException 
	{				
		subject = "您关注的产品降价了";
		message = "尊敬的 "+ name +", 您关注的产品 " + pro.getProductDesc() + " 降价了，欢迎购买!" ;						
	}
	
	protected void emailProcessing(List mailingList, boolean debug) throws Exception	
	{
		
		for(Product pro : products){
		
			setSendMailQuery(pro);		
			
			if (mailingList != null) 
			{
				Iterator iter = mailingList.iterator();
				while (iter.hasNext()) 
				{
					HashMap userInfo = (HashMap) iter.next();				
					setToAddress(userInfo);				
					if (toAddress.length() > 0) 
						setMessage((String)userInfo.get(Configuration.NAME_KEY), pro); 
					
					try 
					{
						if (toAddress.length() > 0)
							sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);
					} 
					catch (Exception e) 
					{
						
						try {
							sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug); 
							
						} catch (Exception e2) 
						{
							System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
						}
					}
				}
			
			}
			else {
				System.out.println("没有邮件发送");			
			}
			
		}
		
		
	}
	
	


	@Override
	protected void readFile(File file) throws IOException // @02C
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String sCurrentLine = "";

			while ((sCurrentLine = br.readLine()) != null) 
			{
				Product prod = new Product();
				String[] data = sCurrentLine.split(" ");			
				prod.setProductID(data[0]); 
				prod.setProductDesc(data[1]); 
				
				System.out.println("产品ID = " + prod.getProductID() + "\n");
				System.out.println("产品描述 = " + prod.getProductDesc() + "\n");			
				products.add(prod);
			}
			
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}
		
}
