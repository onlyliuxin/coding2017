package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {
	protected String sendMailQuery = null;
	protected boolean emailDebug = false;
	protected ProductInfo productInfo = null;
	protected MailAddr mailAddr = null;
	private static Configuration config;

	public static void main(String[] args) throws Exception {
		String path = "D:\\projects\\OOD\\project\\ood-assignment\\config\\product_promotion.txt";

		PromotionMail pe = new PromotionMail(path, false);
		pe.sendEmails();
	}

	public PromotionMail(String path, boolean mailDebug) throws Exception {
		this.emailDebug = mailDebug;
		productInfo = new ProductInfo(path);
		config = new Configuration();
		mailAddr = new MailAddr(config);
	}

	protected void setLoadQuery() throws Exception {
		sendMailQuery = "Select name from subscriptions " + "where product_id= '" + this.productInfo.getProductID()
				+ "' " + "and send_mail=1 ";

		System.out.println("loadQuery set");
	}

	protected MailMsg setMessage(HashMap userInfo) throws IOException {

		String name = (String) userInfo.get(CommonKeys.NAME_KEY);

		String subject = "您关注的产品降价了";
		String message = "尊敬的 " + name + ", 您关注的产品 " + this.productInfo.getProductDesc() + " 降价了，欢迎购买!";

		return new MailMsg(subject, message);
	}

	protected List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}
	
	public void sendEmails() {
		try {
			setLoadQuery();
			sendEMails(emailDebug, loadMailingList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void sendEMails(boolean debug, List mailingList) throws IOException {
		System.out.println("开始发送邮件");

		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				HashMap userInfo = (HashMap) iter.next();
				mailAddr.setToAddress((String) userInfo.get(CommonKeys.EMAIL_KEY));
				if (mailAddr.checkToAddress()) {
					MailMsg mailmsg = setMessage(userInfo);
					try {
						MailUtil.sendEmail(mailAddr, mailmsg, debug);
					} catch (Exception e) {
						try {
							MailUtil.sendEmail(mailAddr, mailmsg, debug);
						} catch (Exception e2) {
							System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
						}
					}
				}
			}
		} else {
			System.out.println("没有邮件发送");
		}

	}
}
