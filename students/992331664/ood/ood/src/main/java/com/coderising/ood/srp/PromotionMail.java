package com.coderising.ood.srp;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.coderising.ood.srp.config.Configuration;
import com.coderising.ood.srp.config.ConnectionConfig;
import com.coderising.ood.srp.model.MailInfo;
import com.coderising.ood.srp.model.Product;
import com.coderising.ood.srp.model.Subscriptions;
import com.coderising.ood.srp.service.ProductService;
import com.coderising.ood.srp.service.SubscriptionsService;
import com.coderising.ood.srp.util.MailUtil;

public class PromotionMail {

	protected SubscriptionsService subscriptionsService;

	protected ProductService productService;

	public PromotionMail(SubscriptionsService subscriptionsService, ProductService productService) {
		this.subscriptionsService = subscriptionsService;
		this.productService = productService;
	}

	/**
	 * 发送促销邮件
	 * 
	 * @param file
	 *            促销产品文件
	 * @param mailDebug
	 * @throws Exception
	 */
	public void sendPromotionMail(File file, boolean mailDebug) throws Exception {

		// 得到促销的产品
		List<Product> products = productService.doFindPromotionalProducts(file);

		// 得到促销产品的订阅信息
		List<Subscriptions> subscriptions = subscriptionsService.doFindByProducts(products);

		// 得到订阅人的邮箱和名称，邮箱内容
		List<MailInfo> mails = getMails(subscriptions);

		// 发送邮箱
		sendEMails(new ConnectionConfig(new Configuration()), mails, mailDebug);
	}

	// 得到发送的邮箱对象
	protected List<MailInfo> getMails(List<Subscriptions> subscriptions) {

		List<MailInfo> mails = new ArrayList<MailInfo>();
		String subject = "您关注的产品降价了";

		for (Subscriptions sub : subscriptions) {
			String productDesc = sub.getProduct().getProductDesc();
			String message = "尊敬的 " + sub.getName() + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";
			mails.add(new MailInfo(subject, message, sub.getEmail()));
		}
		return mails;
	}

	// 发送邮件
	protected void sendEMails(ConnectionConfig config, List<MailInfo> mails, boolean debug) {
		if (mails == null) {
			System.out.println("没有邮件需要发送");
			return;
		}
		System.out.println("开始发送邮件");
		Iterator<MailInfo> iter = mails.iterator();
		while (iter.hasNext()) {
			MailInfo mail = iter.next();
			if (mail.getToAddress().length() <= 0) {
				continue;
			}
			try {
				MailUtil.sendEmail(mail.getToAddress(), config.getFromAddress(), mail.getSubject(), mail.getMessage(),config.getSmtpHost(), debug);
			} catch (Exception e) {
				try {
					MailUtil.sendEmail(mail.getToAddress(), config.getFromAddress(), mail.getSubject(),mail.getMessage(), config.getAltSmtpHost(), debug);

				} catch (Exception e2) {
					System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
				}
			}
		}
		System.out.println("发送邮件结束");
	}
}
