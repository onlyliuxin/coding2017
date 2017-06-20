package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.coderising.ood.srp.dao.SubscriptionDao;
import com.coderising.ood.srp.entity.MailSetting;
import com.coderising.ood.srp.entity.ProductInfo;
import com.coderising.ood.srp.properties.Configuration;
import com.coderising.ood.srp.properties.ConfigurationKeys;
import com.coderising.ood.srp.util.FileUtil;
import com.coderising.ood.srp.util.MailUtil;

public class PromotionMail {

	private boolean mailDebug;


	private static SubscriptionDao subscriptionDao = new SubscriptionDao();

	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";

	public static void main(String[] args) throws Exception {
		// 1 读取配置文件，加载产品信息
		File file = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		boolean emailDebug = false;
		ProductInfo productInfo = new ProductInfo();
		FileUtil.readFileAndSetProductInfo(file, productInfo);

		// 2 设置邮箱服务信息
		Configuration config = new Configuration();
		MailSetting mailSetting = new MailSetting();
		loadMailSetting(config, mailSetting);

		// 3 查询意向用户信息
		subscriptionDao.setLoadQuery(productInfo.getProductID());
		List sendMailList = subscriptionDao.loadMailingList();

		// 4 发送邮件
		PromotionMail pe = new PromotionMail(emailDebug);
		pe.sendEMails(mailSetting, sendMailList, productInfo);

	}

	public PromotionMail( boolean mailDebug) throws Exception {

		this.mailDebug = mailDebug;
	}

	private static void loadMailSetting(Configuration config, MailSetting mailSetting) {

		mailSetting.setAltSmtpHost(config.getProperty(ConfigurationKeys.SMTP_SERVER));
		mailSetting.setAltSmtpHost(config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
		mailSetting.setFromAddress(config.getProperty(ConfigurationKeys.SMTP_SERVER));
	}



	protected void sendEMails(MailSetting mailSetting, List mailingList, ProductInfo productInfo) throws IOException {

		System.out.println("开始发送邮件");
		String subject = "您关注的产品降价了";

		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				Map userInfo = (HashMap) iter.next();
				String userName = (String) userInfo.get(NAME_KEY);
				String toAddress = (String) userInfo.get(EMAIL_KEY);
				String productDesc = productInfo.getProductDesc();
				String message = "尊敬的 " + userName + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";

				try {
					if (toAddress.length() > 0)
						MailUtil.sendEmail(toAddress, mailSetting.getFromAddress(), subject, message,
								mailSetting.getSmtpHost(), this.mailDebug);
				} catch (Exception e) {

					try {
						MailUtil.sendEmail(toAddress, mailSetting.getFromAddress(), subject, message,
								mailSetting.getAltSmtpHost(), this.mailDebug);

					} catch (Exception e2) {
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
					}
				}
			}

		}

		else {
			System.out.println("没有邮件发送");

		}

	}


}
