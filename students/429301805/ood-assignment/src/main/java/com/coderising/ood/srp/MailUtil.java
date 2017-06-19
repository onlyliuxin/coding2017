package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;

public class MailUtil {

	public static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
			boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());
		
	}
	
	public static boolean configureEMail(HashMap userInfo,String toAddress) //throws IOException 
	{
		if (toAddress.length() > 0){ 
			return true;
		}else{
			return false;
		}
	}
	
	public static String setFromAddress(Configuration config) 
	{
			String fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
			return fromAddress;
	}
	
	public static String setToAddress(HashMap userInfo){
	     String toAddress = (String) userInfo.get(ConfigurationKeys.EMAIL_KEY);
	     return toAddress;
	}
	
}
