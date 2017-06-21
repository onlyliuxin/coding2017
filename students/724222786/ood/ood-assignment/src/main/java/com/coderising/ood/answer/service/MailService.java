package com.coderising.ood.answer.service;

import java.util.List;

import com.coderising.ood.answer.entity.MailMessage;
import com.coderising.ood.answer.entity.Product;
import com.coderising.ood.answer.entity.User;
import com.coderising.ood.answer.utils.DBUtils;
import com.coderising.ood.answer.utils.MailUtils;

/**
 * 邮件发送服务
 * @author readke
 *
 */
public class MailService {
	public void sendMail(List<Product> list){
		
		for(Product p: list){
			List<User> uList = DBUtils.queryByProductID(p.getpId());
			for(User u : uList){
				MailMessage m = MailMessage.getMessage("","", "", p, u);
				MailUtils.sendMail(m);
			}
		}
	}
}
