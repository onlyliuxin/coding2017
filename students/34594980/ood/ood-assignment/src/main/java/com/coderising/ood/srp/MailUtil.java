package com.coderising.ood.srp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MailUtil {

	
	private static Configuration config; 
	
	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	

	public static void sendEMails(boolean debug)
	{

		Product product = FileUtil.readFile();
		System.out.println(product.toString());
		
		System.out.println("开始发送邮件");

		List<HashMap<String, String>> mailingList = DBUtil.loadMailingList(product);
		
		if (mailingList == null){
			System.out.println("没有邮件发送");
			return ;
		}

		Iterator<HashMap<String, String>> iter = mailingList.iterator();
		while (iter.hasNext()) {
			HashMap<String, String> userInfo = iter.next();
			Email email = MailUtil.configureEMail(userInfo,product);
			try {
				sendEmail(email.getToAddress(),email.getFromAddress(),email.getSubject(),email.getMessage(),email.getSmtpHost(),debug);
			} catch (Exception e1) {
				try {
					sendEmail(email.getToAddress(),email.getFromAddress(),email.getSubject(),email.getMessage(),email.getAltSmtpHost(),debug);
				} catch (Exception e2) {
					System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
				}
			}
		}

	}
	
	
	public static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,boolean debug) {
		
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());

	}
	
	public static Email configureEMail(HashMap<String, String> userInfo,Product product){
				
		Email email = new Email();
		config = new Configuration();
		email.setSmtpHost(config.getProperty(ConfigurationKeys.SMTP_SERVER));
		email.setAltSmtpHost(config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
		email.setFromAddress(config.getProperty(ConfigurationKeys.EMAIL_ADMIN));
		email.setSubject("您关注的产品降价了");
		String toAddress = (String) userInfo.get(EMAIL_KEY); 
		email.setToAddress(toAddress);
		String name = (String) userInfo.get(NAME_KEY);
		email.setMessage("尊敬的 "+name+", 您关注的产品 " + product.getDesc() + " 降价了，欢迎购买!");
		
		return email;
		
	}	
}
