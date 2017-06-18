package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {
	
	protected String smtpHost = null;
	protected String altSmtpHost = null; 
	protected String fromAddress = null;
	
	protected String toAddress = null;
	protected String subject = null;
	protected String message = null;

	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	

	public static void main(String[] args) throws Exception {

		//C:\\my Program Files\\mygit\\second\\coding2017\\students\\1299310140\\src\\com\\coderising\\ood\\srp
		String productFilePath = "C:\\my Program Files\\mygit\\second\\coding2017\\students\\1299310140\\src\\com\\coderising\\ood\\srp\\product_promotion.txt";
		Product product = ReadFile.readProductFile(productFilePath);
		
		String sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + product.getId() +"' "
				+ "and send_mail=1 ";
		List list = DBUtil.query(sendMailQuery);
		
		if(list == null){
			System.out.println("没有邮件发送");
			return;
		}
		
		Configuration config = new Configuration();
		PromotionMail pe = new PromotionMail(config);
		boolean emailDebug = false;
		
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			HashMap userInfo = (HashMap) iter.next();
			if(CheckUtil.checkEmail((String) userInfo.get(EMAIL_KEY))){
				pe.setMessageAndToAddress(userInfo,product);
				pe.sendEMails(emailDebug);
			}
		}
		
	}

	
	public PromotionMail(Configuration config){
		
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
		
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
	
		fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
		
	}

	protected void setMessageAndToAddress(HashMap userInfo,Product product)
	{
		toAddress = (String) userInfo.get(EMAIL_KEY);
		
		String name = (String) userInfo.get(NAME_KEY);
		
		subject = "您关注的产品降价了";
		message = "尊敬的 "+name+", 您关注的产品 " + product.getDesc() + " 降价了，欢迎购买!" ;		
		
	}
	
	protected void sendEMails(boolean debug) throws IOException 
	{

		System.out.println("开始发送邮件");
		try 
		{
			
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
