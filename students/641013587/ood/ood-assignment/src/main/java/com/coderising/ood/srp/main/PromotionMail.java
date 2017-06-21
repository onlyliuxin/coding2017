package com.coderising.ood.srp.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.coderising.ood.srp.dao.UserDao;
import com.coderising.ood.srp.entity.Product;
import com.coderising.ood.srp.entity.User;
import com.coderising.ood.srp.service.EmailService;
import com.coderising.ood.srp.service.UserService;
import com.coderising.ood.srp.util.FileUtil;
import com.coderising.ood.srp.util.MailUtil;
import com.coderising.ood.srp.util.PropertiesUtil;

public class PromotionMail {

	public static void main(String[] args) throws Exception {
		EmailService emailService = new EmailService();
		UserService userService = new UserService();
		Product product = FileUtil.readRecommendProduct();
		List<User> subscribeUsers = userService.getSubscribeUsers(product);
		emailService.sendEMails(false, subscribeUsers, product);

	}

}
