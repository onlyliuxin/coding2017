package com.coderising.ood.srp;

import java.io.File;
import java.util.List;

public class PromotionMail {


	protected String sendMailQuery = null;


	protected String smtpHost = null;
	protected String altSmtpHost = null; 
	protected String fromAddress = null;
	protected String subject = null;
	protected String message = null;
	

	private static Configuration config; 
	
	
	
	public static void main(String[] args) throws Exception {

		File f = new File("config/product_promotion.txt");
		PromotionMail pe = new PromotionMail();
		List<User> users = DBUtil.query(pe.sendMailQuery);
		Product product = new Product(f);
		pe.sendEMails(users, product);
		
	}

	
	public PromotionMail() throws Exception {
		config = new Configuration();
		setSMTPHost();
		setAltSMTPHost(); 
		setFromAddress();
	}
	
	protected void setSMTPHost() {
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER); 
	}

	protected void setAltSMTPHost() {
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER); 
	}

	
	protected void setFromAddress() {
			fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN); 
	}

	public void setMessage(User user,Product product){
		subject = "您关注的产品降价了";
		message = "尊敬的 "+user.getName()+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;		
	}

	public void sendEMails(User user,Product product){
		setMessage(user,product);
		System.out.println("开始发送邮件");
		MailUtil.sendEmail(user.getEmail(), fromAddress, subject, message, smtpHost, true);
		System.out.println("邮件发送完毕");
	}
	
	public void sendEMails(List<User> users,Product product){
		System.out.println("开始发送邮件");
		for(User user:users){
			setMessage(user,product);
			MailUtil.sendEmail(user.getEmail(), fromAddress, subject, message, smtpHost, true);
		}
		System.out.println("邮件发送完毕");
	}
}
