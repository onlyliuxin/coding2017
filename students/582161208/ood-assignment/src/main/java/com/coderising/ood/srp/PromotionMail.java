package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class PromotionMail {

	protected static String sendMailQuery = "";
	private static Configuration config;

	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";

	public void sendMails(File file, boolean mailDebug) {
		try {
			config = new Configuration();
			sendEMails(file, mailDebug);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static MailInfo setMailInfo() {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setSmtpHost(config.getProperty(ConfigurationKeys.SMTP_SERVER));
		mailInfo.setAltSmtpHost(config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
		mailInfo.setFromAddress(config.getProperty(ConfigurationKeys.EMAIL_ADMIN));
		return mailInfo;
	}

	private static MailInfo setMessage(HashMap<?, ?> userInfo, MailInfo mailInfo, Product product) throws IOException {
		String name = (String) userInfo.get(NAME_KEY);
		mailInfo.setSubject("您关注的产品降价了");
		mailInfo.setMessage("尊敬的 " + name + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!");
		return mailInfo;
	}

	private static Product readFile(File file) throws IOException {
		Product product = setProductInfo(file);
		System.out.println("产品ID = " + product.getProductId() + "\n");
		System.out.println("产品描述 = " + product.getProductDesc() + "\n");
		sendMailQuery = "Select name from subscriptions " + "where product_id= '" + product.getProductId() + "' "
				+ "and send_mail=1 ";
		System.out.println("loadQuery set");
		return product;
	}

	private static Product setProductInfo(File file) throws IOException {
		Product product = null;
		BufferedReader br = null;
		try {
			product = new Product();
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			product.setProductId(data[0]);
			product.setProductDesc(data[1]);
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		return product;
	}

	private static MailInfo configureEMail(HashMap<?, ?> userInfo, MailInfo mailInfo, Product product) throws IOException {
		String toAddress = (String) userInfo.get(EMAIL_KEY);
		mailInfo.setToAddress(toAddress);
		if (toAddress.length() > 0)
			return setMessage(userInfo, mailInfo, product);
		return mailInfo;
	}

	private static void sendEMails(File file, boolean debug) throws IOException {

		Product product = readFile(file);

		MailInfo mailInfo = setMailInfo();

		System.out.println("开始发送邮件");

		List<?> mailingList = DBUtil.query(sendMailQuery);
		if (CollectionUtils.isNotEmpty(mailingList)) {
			Iterator<?> iter = mailingList.iterator();
			while (iter.hasNext()) {
				MailInfo newMail = configureEMail((HashMap<?, ?>) iter.next(), mailInfo, product);
				try {
					if (StringUtils.isNotBlank(newMail.getToAddress()))
						sendMail(mailInfo, debug);
				} catch (Exception e) {
					try {
						sendMail(mailInfo, debug);
					} catch (Exception e2) {
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
					}
				}
			}

		} else {
			System.out.println("没有邮件发送");
		}

	}

	private static void sendMail(MailInfo mailInfo, Boolean debug) {
		MailUtil.sendEmail(mailInfo.getToAddress(), mailInfo.getFromAddress(), mailInfo.getSubject(),
				mailInfo.getMessage(), mailInfo.getSmtpHost(), debug);
	}

	public static void main(String[] args) throws Exception {
		// File f = new
		// File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		File file = new File("/Users/dianping/Desktop/product_promotion.txt");
		boolean emailDebug = false;
		PromotionMail pe = new PromotionMail();
		pe.sendMails(file, emailDebug);
	}

}
