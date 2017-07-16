package com.coderising.ood.srp_restructure_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.coderising.ood.srp_restructure_1.pojo.Configuration;
import com.coderising.ood.srp_restructure_1.pojo.ConfigurationKeys;
import com.coderising.ood.srp_restructure_1.pojo.Mail;
import com.coderising.ood.srp_restructure_1.pojo.MailServiceConfiguration;
import com.coderising.ood.srp_restructure_1.pojo.Product;
import com.coderising.ood.srp_restructure_1.pojo.User;
import com.coderising.ood.srp_restructure_1.service.ProductService;
import com.coderising.ood.srp_restructure_1.service.UserService;
import com.coderising.ood.srp_restructure_1.util.DBUtil;
import com.coderising.ood.srp_restructure_1.util.MailUtil;

public class PromotionMail {

	UserService userService = new UserService();
	ProductService productService = new ProductService();

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/java/com/coderising/ood/srp_restructure_1/product_promotion.txt");
		boolean emailDebug = false;
		PromotionMail pe = new PromotionMail(file, emailDebug);
	}

	public PromotionMail(File file, boolean mailDebug) throws Exception {
		MailServiceConfiguration configuration = new MailServiceConfiguration()
				.setAltSMTPHost(ConfigurationKeys.SMTP_SERVER).setSMTPHost(ConfigurationKeys.ALT_SMTP_SERVER)
				.setFromAddress(ConfigurationKeys.SMTP_SERVER);
		List<Product> plist = productService.getProductDescList(file);
		sendEMails(mailDebug, configuration, plist);
	}

	protected void sendEMails(boolean debug, MailServiceConfiguration configuration, List<Product> plist)
			throws IOException {
		System.out.println("开始发送邮件");
		if (plist != null) {
			Iterator<Product> piterator = plist.iterator();
			while (piterator.hasNext()) {
				Product product = piterator.next();
				List<User> ulist = userService.getSendMailUser(product);
				if (ulist != null) {
					Iterator<User> uiterator = ulist.iterator();
					while (uiterator.hasNext()) {
						User user = uiterator.next();
						Mail mail = new Mail("您关注的产品降价了",
								"尊敬的 " + user.getName() + ", 您关注的产品 " + plist.get(0).getProductDesc() + " 降价了，欢迎购买!",
								user.getEmail());
						MailUtil.sendEmail(debug, configuration, mail);
					}
				} else {
					System.out.println("没有邮件发送");
				}
			}
		} else {
			System.out.println("没有降价商品");
		}
	}
}
