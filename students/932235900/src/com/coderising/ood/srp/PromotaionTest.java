package com.coderising.ood.srp;

import java.util.List;

import com.coderising.ood.srp.common.Configuration;
import com.coderising.ood.srp.entity.Email;
import com.coderising.ood.srp.entity.Product;
import com.coderising.ood.srp.entity.User;
import com.coderising.ood.srp.service.EmailService;
import com.coderising.ood.srp.service.ProductService;
import com.coderising.ood.srp.service.UserService;

public class PromotaionTest {

	public static void main(String[] args) {
		//1.获得客户集合
		List<User> users = new UserService().getUsers();
		//2.获得促销商品
		List<Product> products = new ProductService().getPromotionProducts("src\\com\\coderising\\ood\\srp\\common\\product_promotion.txt");
		//3.配置
		Configuration conf = new Configuration();
		//给每个用户发邮件
		EmailService emailService = new EmailService();
		for(User user:users ){
				
				Email email = emailService.generateEmail(user, products, conf );
				
				emailService.sendEmail(email);
				
			
		}
		
	}
}
