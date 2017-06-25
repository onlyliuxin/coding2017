package com.coderising.ood.srp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp.config.Configuration;
import com.coderising.ood.srp.model.MailInfo;
import com.coderising.ood.srp.model.Product;
import com.coderising.ood.srp.model.Subscriptions;
import com.coderising.ood.srp.model.User;
import com.coderising.ood.srp.service.MailService;
import com.coderising.ood.srp.service.ProductService;
import com.coderising.ood.srp.service.SubscriptionsService;
import com.coderising.ood.srp.service.impl.MailServiceImpl;

public class PromotionMail {

	protected SubscriptionsService subscriptionsService;

	protected ProductService productService;

	protected MailService mailService;

	public PromotionMail(SubscriptionsService subscriptionsService, ProductService productService) {
		this.subscriptionsService = subscriptionsService;
		this.productService = productService;
		this.mailService = new MailServiceImpl(new Configuration());
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
		sendEmails(mails);
	}

	// 得到发送的邮箱对象
	protected List<MailInfo> getMails(List<Subscriptions> subscriptions) {
		List<MailInfo> mails = new ArrayList<MailInfo>();
		String subject = "您关注的产品降价了";

		for (Subscriptions sub : subscriptions) {
			String productDesc = sub.getProduct().getProductDesc();
			User user = sub.getUser();
			String message = "尊敬的 " + user.getName() + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";
			mails.add(new MailInfo(subject, message, user.getEmail()));
		}
		return mails;
	}

	// 发送邮件
	protected void sendEmails(List<MailInfo> mails) {
		if (mails == null) {
			System.out.println("没有邮件需要发送");
			return;
		}
		for (MailInfo mail : mails) {
			mailService.sendMail(mail);
		}
		System.out.println("发送邮件结束");
	}
}
