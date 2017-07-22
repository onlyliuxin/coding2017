package com.coderising.myood.srp.goodSrp;

import java.util.List;

public class PromotionJob {
	
	private ProductService productService = new ProductService() ; //获取production service
	private UserService userService = new UserService() ;// 获取UserService
	
	public void run(){
		
		Configuration cfg = new Configuration();
		
		List<Product> ps = productService.getPromotionProducts();
		
		List<User> users = userService.getUsers(ps);

		
		MailSender mailSender = new MailSender(cfg);
		
		for(User user : users){
			mailSender.sendMail(new Mail(user));
		}
	}

    public static void main(String[] args) {
        new PromotionJob().run();
    }
}
