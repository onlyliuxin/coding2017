package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {

	List mailingList = null;
	private static Configuration config;

	private Product product = null;

	public static void main(String[] args) throws Exception {
		File f = new File("src/com/coderising/ood/srp/product_promotion.txt");
		boolean emailDebug = false;

		PromotionMail pe = new PromotionMail(f, emailDebug);

	}

	public PromotionMail(File file, boolean mailDebug) throws Exception {

		init(file, mailDebug);

	}

	private void init(File file, boolean mailDebug) throws Exception {
		product = new Product(file);
		config = new Configuration();
		MailingDao dao = new MailingDao();
		mailingList = dao.getQuery(product.getProductID());

		sendEMails(mailDebug, mailingList);

	}

	protected void sendEMails(boolean debug, List mailingList)
			throws IOException {

		System.out.println("开始发送邮件");

		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				ConfigureEmail ce = new ConfigureEmail(config, product,
						(HashMap) iter.next());
				EmailBean email = ce.getEmail();
				try {
					if (email.getToAddress().length() > 0)
						MailUtil.sendEmail(email, debug);
				} catch (Exception e) {

					try {
						MailUtil.sendEmail(email, debug);

					} catch (Exception e2) {
						System.out.println("通过备用 SMTP服务器发送邮件失败: "
								+ e2.getMessage());
					}
				}
			}

		}

		else {
			System.out.println("没有邮件发送");

		}

	}
}
