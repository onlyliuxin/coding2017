package com.coderising.ood.srp.service;

import java.util.List;

import com.coderising.ood.srp.common.Configuration;
import com.coderising.ood.srp.common.ConfigurationKeys;
import com.coderising.ood.srp.entity.Email;
import com.coderising.ood.srp.entity.Product;
import com.coderising.ood.srp.entity.User;

public class EmailService {
	
	public Email generateEmail(User user,List<Product> products,Configuration configuration){
		Email email = new Email();
		email.setFromAddress(configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN));
		email.setToAddress(user.getEmailAddress());
		email.setSubject("您关注的产品降价了");
		StringBuffer message=new StringBuffer("尊敬的 "+user.getUserName()+", 您关注的产品:");
		for(Product product:products){
			message.append(product.getProductDesc()+" ");
		}
		message.append("降价了，欢迎购买!");
		email.setMessage(message.toString());
		return email;
	}
	/**
	 * 发送一封Email
	 * @param email
	 */
	public void sendEmail(Email email){
		if(email != null ){
			//假装发了一封邮件
			StringBuilder buffer = new StringBuilder();
			buffer.append("From:").append(email.getFromAddress()).append("\n");
			buffer.append("To:").append(email.getToAddress()).append("\n");
			buffer.append("Subject:").append(email.getSubject()).append("\n");
			buffer.append("Content:").append(email.getMessage()).append("\n");
			System.out.println(buffer.toString());
		}else{
			System.out.println("email 为空，发送邮件失败！");
		}
	}
}
