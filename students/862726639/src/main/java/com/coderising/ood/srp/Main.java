package com.coderising.ood.srp;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class Main {
	//发送邮件
	@Test
	public void test() throws Exception{
		//获得降价商品
		Goods goods = new Goods();
		ArrayList<Goods> saleGoods = goods.getSaleGoods();
		//获得对应用户
		for (Goods goods2 : saleGoods) {
			User user = new User();
			ArrayList<User> userById = user.getUserById(goods2.getProductID());
			for (User user2 : userById) {
				//发送邮件
				Email email = new Email();
				email.setHost("smtp.163.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的  
				email.setSender("15237140070@163.com");  
				email.setReceiver(user2.getEmail()); // 接收人  
				email.setUsername("15237140070@163.com"); // 登录账号,一般都是和邮箱名一样吧  
				email.setPassword("sudan521"); // 发件人邮箱的登录密码  
				email.setSubject("降价了降价了");  
				email.setMessage(Message.saleMessage(user2.getName(), goods2.getProductDesc())+"");  
				email.send2(email);  
				
			}
		}
	}

}
