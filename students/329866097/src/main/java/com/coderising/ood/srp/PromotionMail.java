package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PromotionMail {

	public static void main(String[] args) throws Exception {
		Email email = new Email();

		Map<String, String> productMap = FileUtil.readFile( "src/main/resources/product_promotion.txt");
		for (Map.Entry<String, String> entry : productMap.entrySet()) {
			String productId = entry.getKey();
			String productDesc = entry.getValue();
			List<User> userList = DBUtil.query(setLoadQuery(productId));
			for(User user : userList) {
				try {
					email.send(user, productDesc, email.getSmtpHost());
				} catch (Exception e) {
					try {
						email.send(user, productDesc, email.getAltSmtpHost());
					} catch (Exception e2) {
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
					}
				}
			}
		}
	}

	private static String setLoadQuery(String productID) throws Exception {
		
		String sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productID +"' "
				+ "and send_mail=1 ";

		System.out.println("loadQuery set");

		return sendMailQuery;
	}
}
