package com.coderising.ood.srp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MailUtil {

	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	
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
	
	//配置发送地址及内容
	public static HashMap configureEMail(HashMap userInfo , String productDesc) throws IOException {
		HashMap map = new HashMap();
		String toAddress = (String) userInfo.get(EMAIL_KEY); 
		if (toAddress.length() > 0) {
			map = setMessage(userInfo,productDesc); 
		}
		map.put("toAddress", toAddress);
		return map;
	}

	//设置信息内容
	public static HashMap setMessage(HashMap userInfo , String productDesc) throws IOException 
	{
		HashMap map = new HashMap();
		String name = (String) userInfo.get(NAME_KEY);
		
		String subject = "您关注的产品降价了";
		String message = "尊敬的 "+name+", 您关注的产品 " + productDesc + " 降价了，欢迎购买!" ;		
		
		map.put("subject", subject);
		map.put("message", message);
		
		return map;

	}
	
	
	
}
