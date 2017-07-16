package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MailContent {

	private String subject = null;
	private String message = null;
	private String toAddress = null;
	private final String NAME_KEY = "NAME";
	private final String EMAIL_KEY = "EMAIL";
	
	public MailContent(File f, HashMap userInfo, String prodInfo){
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		try {
			configureEMail(userInfo, prodInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void setMessage(HashMap userInfo, String prodInfo) throws IOException 
	{
		
		String name = (String) userInfo.get(NAME_KEY);
		
		subject = "您关注的产品降价了";
		message = "尊敬的 "+name+", 您关注的产品 " + prodInfo + " 降价了，欢迎购买!" ;		
				
	}
	
	protected void configureEMail(HashMap userInfo, String prodInfo) throws IOException 
	{
		toAddress = (String) userInfo.get(EMAIL_KEY); 
		if (toAddress.length() > 0) 
			setMessage(userInfo, prodInfo); 
	}
	
	public String getToAddress() {
		return toAddress;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getMessage() {
		return message;
	}
}
