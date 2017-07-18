package com.github.wluqing.coding2017.basic.ood.srp;

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


	protected String smtpHost = null;
	protected String altSmtpHost = null; 
	protected String fromAddress = null;
	protected String toAddress = null;
	protected String subject = null;
	protected String message = null;

	protected String productID = null;
	protected String productDesc = null;

	private static Configuration config; 
	
	
	
	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	

	public static void main(String[] args) throws Exception {

		File f = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		boolean emailDebug = false;

		PromotionMail pe = new PromotionMail(f, emailDebug);

	}

	
	public PromotionMail(File file, boolean mailDebug) throws Exception {
		
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		readFile(file);

		
		config = new Configuration();
		
		setSMTPHost();
		setAltSMTPHost(); 
	

		setFromAddress();

		
		setLoadQuery();
		
		sendEMails(mailDebug, loadMailingList()); 

		
	}




	protected void setProductID(String productID) 
	{ 
		this.productID = productID; 
		
	} 

	protected String getproductID() 
	{
		return productID; 
	} 

	protected void setLoadQuery() throws Exception {
		
		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productID +"' "
				+ "and send_mail=1 ";
		
		
		System.out.println("loadQuery set");
	}

	
	protected void setSMTPHost() 
	{
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER); 
	}

	
	protected void setAltSMTPHost() 
	{
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER); 

	}

	
	protected void setFromAddress() 
	{
			fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN); 
	}

	protected void setMessage(HashMap userInfo) throws IOException 
	{
		
		String name = (String) userInfo.get(NAME_KEY);
		
		subject = "您关注的产品降价了";
		message = "尊敬的 "+name+", 您关注的产品 " + productDesc + " 降价了，欢迎购买!" ;		
				
		

	}

	
	protected void readFile(File file) throws IOException // @02C
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			setProductID(data[0]); 
			setProductDesc(data[1]); 
			
			System.out.println("产品ID = " + productID + "\n");
			System.out.println("产品描述 = " + productDesc + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}

	private void setProductDesc(String desc) {
		this.productDesc = desc;		
	}


	protected void configureEMail(HashMap userInfo) throws IOException 
	{
		toAddress = (String) userInfo.get(EMAIL_KEY); 
		if (toAddress.length() > 0) 
			setMessage(userInfo); 
	}

	protected List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}
	
	
	protected void sendEMails(boolean debug, List mailingList) throws IOException 
	{

		System.out.println("开始发送邮件");
	

		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				configureEMail((HashMap) iter.next());  
				try 
				{
					if (toAddress.length() > 0)
						MailUtil.sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);
				} 
				catch (Exception e) 
				{
					
					try {
						MailUtil.sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug); 
						
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
