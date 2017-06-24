package com.coderising.ood.srp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PromotionMail {

	public static void main(String[] args) {
		
		/**
		 * 整体步骤大概有3步：
		 * 1、读取配置文件，得到促销的商品列表
		 * 2、调用DBUtil读取DB，得到订阅的用户列表
		 * 3、调用MailUtil发送邮件
		 */
		List<Product> products = new ArrayList<Product>();
		List<User> users = new ArrayList<User>();
		try {
			
			products = ProductUtil.getProducts();
			users = DBUtil.queryUserList("select *** from t_user");
			MailUtil.sendEmail(products, users);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
