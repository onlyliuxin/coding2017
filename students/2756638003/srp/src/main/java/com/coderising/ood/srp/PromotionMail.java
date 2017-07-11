package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.coderising.ood.srp.conf.Configuration;
import com.coderising.ood.srp.conf.ConfigurationKeys;
import com.coderising.ood.srp.conf.EmailStatus;
import com.coderising.ood.srp.domain.Product;
import com.coderising.ood.srp.domain.Subscriber;
import com.coderising.ood.srp.util.ConfigUtil;
import com.coderising.ood.srp.util.Email;
import com.coderising.ood.srp.util.MailUtil;
import com.coderising.ood.srp.util.SubscriberUtil;

public class PromotionMail {

	protected String smtpHost = null;
	protected String altSmtpHost = null;
	protected String fromAddress = null;

	private static Configuration config;

	public static void main(String[] args) throws Exception {
		File file = new File("XX/product_promotion.txt");
		boolean emailDebug = false;
		new PromotionMail(file, emailDebug);
	}

	/**
	 * 从配置文件中读取商品信息
	 */
	private List<Product> loadFile(File file) throws IOException {
		Map<String, String> conf = ConfigUtil.readTextFile(file);
		List<Product> productList = new ArrayList<Product>(16);
		Set<Entry<String, String>> entrySet = conf.entrySet();
		for (Entry<String, String> entry : entrySet) {
			productList.add(new Product(entry.getKey(), entry.getValue()));
		}
		return productList;
	}

	/**
	 * 根据商品查询订阅的用户信息
	 */
	private List<Subscriber> querySubscribersFormFile(List<Product> productList)
			throws IOException {
		StringBuilder query = new StringBuilder(
				"Select name from Subscriber where product_id in(  ");
		for (int i = 0, len = productList.size(); i < len - 1; i++) {
			query.append(productList.get(i).getProductID() + " ,");
		}
		query.append(productList.get(productList.size() - 1).getProductID());
		query.append(") and send_mail = ?");
		return SubscriberUtil.loadSubscriberList(query.toString(),
				EmailStatus.READY);
	}

	/**
	 * 根据订阅者信息生成邮件
	 */
	private Email generatorEmail(Subscriber subscriber, String host) {
		String subject = "您关注的产品降价了";
		String message = "尊敬的 " + subscriber.getName() + ", 您关注的产品 "
				+ subscriber.getProduct().getProductDesc() + " 降价了，欢迎购买!";
		return new Email(subscriber.getEmail(), fromAddress, subject, message,
				host);
	}

	public PromotionMail(File file, boolean mailDebug) throws Exception {
		config = new Configuration();
		setSMTPHost();
		setAltSMTPHost();
		setFromAddress();
		// 从配置文件中读取商品信息
		List<Product> productList = loadFile(file);
		// 根据商品查询订阅的用户信息
		List<Subscriber> subscriberList = querySubscribersFormFile(productList);
		// 发送邮件
		sendEMails(mailDebug, subscriberList);
	}

	/**
	 * 发送邮件
	 */
	protected void sendEMails(boolean debug, List<Subscriber> subscriberList)
			throws IOException {
		System.out.println("开始发送邮件");
		if (subscriberList != null && subscriberList.size() > 0) {
			Iterator<Subscriber> iter = subscriberList.iterator();
			Subscriber subscriber = null;

			while (iter.hasNext()) {
				subscriber = iter.next();
				try {
					MailUtil.sendEmail(generatorEmail(subscriber, smtpHost),
							debug);
				} catch (Exception e) {
					try {
						MailUtil.sendEmail(
								generatorEmail(subscriber, altSmtpHost), debug);
					} catch (Exception e2) {
						System.out.println("通过备用 SMTP服务器发送邮件失败: "
								+ e2.getMessage());
					}
				}
			}
		} else {
			System.out.println("没有邮件发送");
		}
	}

	protected void setSMTPHost() {
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
	}

	protected void setAltSMTPHost() {
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
	}

	protected void setFromAddress() {
		fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}

}
