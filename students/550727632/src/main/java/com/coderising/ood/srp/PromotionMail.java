package com.coderising.ood.srp;

import java.io.File;

public class PromotionMail {
	private String subject;
	private String message;

	public PromotionMail() {
		subject = "您关注的产品降价了";
	}

	public static void main(String[] args) throws Exception {
		File f = new File("src/main/java/com/coderising/ood/srp/product_promotion.txt");
		boolean emailDebug = false;
		Product product = FileUtil.readProductFile(f);
		PromotionMail mail = new PromotionMail();
		MailUtil.sendPromotionEmail(mail, product, emailDebug);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessage(User user, Product product) {
		message = "尊敬的 " + user.getName() + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!";
	}

	public String getMessage() {
		return message;
	}

}
