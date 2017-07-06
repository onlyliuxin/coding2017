package com.coderising.ood.srp.chasing;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.coderising.ood.srp.chasing.model.Product;
import com.coderising.ood.srp.chasing.model.User;
import com.coderising.ood.srp.chasing.service.MailService;
import com.coderising.ood.srp.chasing.service.ProductService;
import com.coderising.ood.srp.chasing.service.PromotionService;
import com.coderising.ood.srp.chasing.service.UserService;

public class PromotionMail {
	private MailService mailServer = new MailService(new Configuration());;
	private ProductService productService = null;
	private UserService userService = new UserService();
	private PromotionService promptService = new PromotionService();

	public static void main(String[] args) throws Exception {
		File f = new File("D:\\coding2017\\students\\282692248\\ood\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\chasing\\product_promotion.txt");
		PromotionMail pe = new PromotionMail(f);
		pe.sendPromptMails();
	}
	
	public PromotionMail(File file){
		productService = new ProductService(file);
	}
	
	/** 主要业务逻辑：发送促销信息 */
	protected void sendPromptMails() throws IOException {
		System.out.println("开始发送邮件");
		Product product = productService.loadProduct();
		List<User> users = userService.loadUserByProduct(product);
		if (users != null) {
			for(User user:users){
				mailServer.sendMail(user.getEmail(), promptService.getPromptProfile(), 
						promptService.buildPromptMessageForUser(product, user));
			}
		}
		else {
			System.out.println("没有邮件发送");
		}
	}
}
