package com.coderising.ood.srp.domainlogic;

import java.io.IOException;
import java.util.List;

/**
 * 促销邮件发送服务，实现具体的邮件发送业务逻辑
 * 
 * @author silencehe09
 *
 */
public class PromotionEmailService {
	private EmailService emailService;
	private ProductRepository productRepository;
	private UserRepository userRepository;
	private String fromAddress;

	public PromotionEmailService(EmailService emailService, ProductRepository productRepository,
			UserRepository userRepository) {
		this.emailService = emailService;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	public void sendEmail() throws IOException {
		List<User> users = userRepository.getPromotionUsers();
		List<Product> products = productRepository.getPromotionProducts();
		for (Product product : products) {
			for (User user : users) {
				Email email = new Email();
				email.setFromAddress(fromAddress);
				email.setToAddress(user.getEmail());
				email.setSubject(getEmailSubject());
				email.setMessage(getEmailMessage(user, product));
				emailService.sendEmail(email);
			}
		}
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	private String getEmailSubject() {
		return "您关注的产品降价了";
	}

	private String getEmailMessage(User user, Product product) {
		return "尊敬的 " + user.getName() + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!";
	}
}
