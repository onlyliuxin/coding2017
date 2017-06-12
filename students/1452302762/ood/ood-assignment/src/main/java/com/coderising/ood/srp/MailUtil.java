package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MailUtil {
	private static String smtpHost = Configuration.getProperty(ConfigurationKeys.SMTP_SERVER);;
	private static String altSmtpHost = Configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER); ; 
	private static String fromAddress = Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN); ;
	private static String toAddress = null;
	private static String subject = null;
	private static String message = null;
	protected void setMessage(String name,String productDesc) throws IOException{
		if(toAddress.length()>0){
			subject = "您关注的产品降价了";
			message = "尊敬的 "+name+", 您关注的产品 " + productDesc + " 降价了，欢迎购买!" ;		
		}
	}
	private static  List loadMailingList(String productID) throws Exception {
		String sendMailQuery = "Select name from subscriptions where product_id= '" + productID +"and send_mail=1 ";
		System.out.println("loadQuery set");
		return DBUtil.query(sendMailQuery);
	}
	protected static void configureEMail(HashMap<String,String> userInfo) throws IOException{
		toAddress =userInfo.get(Constant.EMAIL_KEY); 
	}
	public  static void sendEMails(boolean debug,String productID) throws Exception{
		System.out.println("开始发送邮件");
		List mailingList=loadMailingList(productID);
		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				configureEMail((HashMap) iter.next());  
				try{
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
	private static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
			boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());
	}
}
