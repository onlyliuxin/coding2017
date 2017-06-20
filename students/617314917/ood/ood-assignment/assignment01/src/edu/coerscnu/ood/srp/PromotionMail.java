package edu.coerscnu.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 促销邮件类
 * 
 * 1、设置邮件主题
 * 
 * 2、设置邮件正文
 * 
 * 3、设置邮件收件人列表
 * 
 * 4、发送邮件
 * 
 * @author xujie
 *
 */
public class PromotionMail {

	protected String subject = null;
	protected String message = null;
	protected List<HashMap<String, String>> mailList;

	public static void main(String[] args) throws Exception {

		String path = "src/edu/coerscnu/ood/srp/product_promotion.txt";
		List<String[]> productList = FileUtil.readFile(path);
		for (String[] prod : productList) {
			Product product = new Product(prod[0], prod[1]);
			PromotionMail pm = new PromotionMail();
			pm.setMailList(product);
			pm.sendMails(product);
		}
	}

	/**
	 * 设置邮件主题
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 设置邮件正文
	 * 
	 * @param userInfo
	 * @param product
	 * @throws IOException
	 */
	protected void setMessage(HashMap<String, String> userInfo, Product product) throws IOException {
		String name = (String) userInfo.get(UserService.NAME_KEY);
		String desc = product.getProductDesc();
		message = "尊敬的 " + name + ", 您关注的产品 " + desc + " 降价了，欢迎购买!";
	}

	/**
	 * 设置收件人列表
	 * 
	 * @param product
	 * @throws Exception
	 */
	protected void setMailList(Product product) throws Exception {
		UserService userService = new UserService();
		userService.setLoadQuery(product);
		mailList = userService.loadMailingList();
	}

	/**
	 * 发送邮件
	 * 
	 * @throws IOException
	 */
	protected void sendMails(Product product) throws IOException {
		if (mailList != null) {
			Iterator<HashMap<String, String>> iter = mailList.iterator();
			while (iter.hasNext()) {
				HashMap<String, String> user = iter.next();
				String toAddress = (String) user.get(UserService.MAIL_KEY);
				if (toAddress.length() > 0) {
					setSubject("您关注的产品降价了");
					setMessage(user, product);
					MailUtil.sendMail(toAddress, subject, message);
				}
			}
		}
	}
}
