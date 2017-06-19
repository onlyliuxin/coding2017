package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PromotionMail {

	protected String sendMailQuery = null;

	protected String smtpHost = null;
	protected String altSmtpHost = null;
	protected String fromAddress = null;
	protected String toAddress = null;
	protected String subject = null;
	protected String message = null;

	protected String productID = null;
	protected String productDesc = null;

	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";

	public static void main(String[] args) throws Exception {

		File f = new File("G:\\Java\\github\\coding2017\\students\\404481481\\day01\\src\\product_promotion.txt");
		boolean emailDebug = false;

		PromotionMail pe = new PromotionMail(f, emailDebug);

	}

	public PromotionMail(File file, boolean mailDebug) throws Exception {

		// 读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		FileUtil.readFile(file, this);
		Configuration.init(this);
		setLoadQuery();

		MailUtil.sendEMails(mailDebug, loadMailingList(), this);

	}

	protected void setLoadQuery() throws Exception {
		sendMailQuery = "Select name from subscriptions " + "where product_id= '" + productID + "' "
				+ "and send_mail=1 ";
		System.out.println("loadQuery set");
	}

	protected void setMessage(HashMap userInfo) throws IOException {
		String name = (String) userInfo.get(NAME_KEY);
		subject = "您关注的产品降价了";
		message = "尊敬的 " + name + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";
	}

	protected void configureEMail(HashMap userInfo) throws IOException {
		toAddress = (String) userInfo.get(EMAIL_KEY);
		if (toAddress.length() > 0)
			setMessage(userInfo);
	}

	protected List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}

}
