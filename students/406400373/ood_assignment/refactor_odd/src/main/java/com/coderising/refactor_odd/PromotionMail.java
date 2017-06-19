package com.coderising.refactor_odd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coderising.refactor_odd.entity.EmailEntity;
import com.coderising.refactor_odd.entity.ProductEntity;
import com.coderising.refactor_odd.entity.UserEntity;
import com.coderising.refactor_odd.handler.EmailHandler;
import com.coderising.refactor_odd.handler.ProductHandler;
import com.coderising.refactor_odd.handler.UserHandler;
import org.apache.commons.collections4.CollectionUtils;

public class PromotionMail {

	public static void main(String[] args) throws Exception {
		// 初始化处理器
		EmailHandler emailHandler = new EmailHandler();
		ProductHandler productHandler = new ProductHandler();
		UserHandler userHandler = new UserHandler();

		// 构造数据
		List<EmailEntity> emailEntities = new ArrayList<>();
		List<ProductEntity> products = productHandler.fetchProducts();
		for (ProductEntity product : products) {
			List<UserEntity> users = userHandler.fetchUser(buildUserQuery(product.getProductId()));
			for (UserEntity user : users) {
				EmailEntity emailEntity = new EmailEntity();
				emailEntity.setToAddress(user.getEmail());
				emailEntity.setSubject("您关注的产品降价了");
				emailEntity.setMessage("尊敬的 " + user.getName() + ", 您关注的产品 " + product.getProductName() + " 降价了，欢迎购买!");
				emailEntities.add(emailEntity);
			}
		}
		// 发送邮件
		sendEMails(false, emailEntities, emailHandler);

	}

	private static String buildUserQuery(String productID) throws Exception {

		return "Select name from subscriptions " + "where product_id= '" + productID + "' " + "and send_mail=1 ";
	}

	private static void sendEMails(boolean debug, List<EmailEntity> emailEntities, EmailHandler emailHandler)
			throws IOException {

		System.out.println("开始发送邮件");
		if (CollectionUtils.isNotEmpty(emailEntities)) {
			for (EmailEntity emailEntity : emailEntities) {
				System.out.println("发送邮件:");
				emailHandler.sendMail(emailEntity, debug);
			}
		} else {
			System.out.println("没有邮件发送");
		}
	}
}
