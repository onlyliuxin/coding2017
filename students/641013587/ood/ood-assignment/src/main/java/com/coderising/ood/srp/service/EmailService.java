package com.coderising.ood.srp.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.coderising.ood.srp.constant.CommonConstant;
import com.coderising.ood.srp.entity.Msg;
import com.coderising.ood.srp.entity.Product;
import com.coderising.ood.srp.entity.User;
import com.coderising.ood.srp.util.MailUtil;
import com.coderising.ood.srp.util.PropertiesUtil;

public class EmailService {
	
	public boolean sendEMails(boolean debug, List<User> mailingList, Product product) throws IOException 
	{

		System.out.println("开始发送邮件");
		Msg basemsg = PropertiesUtil.BASEMSG;
		for (User user : mailingList) {
			try 
			{
				basemsg.setToAddress(user.getEmail());
				basemsg.setSubject(CommonConstant.SUBJECT);
				basemsg.setMessage(CommonConstant.getProductMessage(user, product));
				if (basemsg.getToAddress().length() > 0)
					MailUtil.sendEmail(debug,basemsg);
			} 
			catch (Exception e) 
			{
				
				try {
					MailUtil.sendEmail(debug,basemsg);
					
				} catch (Exception e2) 
				{
					System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
				}
				return false;
			}
			
		}
		return true;
		
	}
	
}
