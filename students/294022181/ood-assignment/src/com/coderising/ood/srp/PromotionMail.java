package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {
	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	
	private Configuration config;
	private ProductDao productDao;
	private SubscriptionDao subscriptionDao;
	
	public static void main(String[] args) throws Exception {
		boolean emailDebug = false;
		String productFileName = "./product_promotion.txt";
		PromotionMail pe = new PromotionMail(productFileName, emailDebug);
	}
	
	public PromotionMail(String productFileName, boolean mailDebug) throws Exception {
		config = new Configuration();
		productDao = new ProductDao();
		subscriptionDao = new SubscriptionDao();
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		Product product = productDao.getProduct(productFileName);
		
		if (product == null) {
			return;
		}
		
		sendEMails(mailDebug, product, subscriptionDao.loadMailingList(product.getProductID())); 
	}

	protected void sendEMails(boolean debug, Product product, List mailingList) throws IOException {

		System.out.println("开始发送邮件");
	
		if (mailingList != null) {
			Email email = new Email();
			String smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER); 
			String altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER); 
			String fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
			Iterator iter = mailingList.iterator();
			
			while (iter.hasNext()) {
				HashMap userInfo = (HashMap) iter.next();
				String toAddress = (String) userInfo.get(EMAIL_KEY); 
				
				if (toAddress.length() > 0) {
					String name = (String) userInfo.get(NAME_KEY);
					String subject = "您关注的产品降价了";
					String message = "尊敬的 " + name + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;
					email.setFromAddress(fromAddress);
					email.setToAddress(toAddress);
					email.setSmtpHost(smtpHost);
					email.setAltSmtpHost(altSmtpHost);
					email.setSubject(subject);
					email.setMessage(message);
					email.setDebug(debug);
					email.sendToTarget();
				}
			}
		} else {
			System.out.println("没有邮件发送");
		}

	}
}
