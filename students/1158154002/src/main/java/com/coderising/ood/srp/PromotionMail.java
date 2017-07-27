package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {

	protected HandlerEmail hanlder;

	public static void main(String[] args) throws Exception {

		File f = new File(
				"D:\\mygit\\coding2017\\students\\1158154002\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
		boolean emailDebug = false;

		PromotionMail pe = new PromotionMail(f, emailDebug);

	}

	public PromotionMail(File file, boolean mailDebug) throws Exception {
		
		hanlder = new HandlerEmail(file);
		hanlder.setLoadQuery();
		sendEMails(mailDebug, hanlder.loadMailingList());

	}

	protected void sendEMails(boolean debug, List mailingList) throws IOException {

		System.out.println("开始发送邮件");

		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				hanlder.configureEMail((HashMap) iter.next());
				try {
					if (hanlder.mail.getToAddress().length() > 0)
						MailUtil.sendEmail(hanlder.mail.getToAddress(), hanlder.mail.getFromAddress(), hanlder.mail.getSubject(),
								hanlder.mail.getMessage(), hanlder.mail.getSmtpHost(), debug);
				} catch (Exception e) {

					try {
						MailUtil.sendEmail(hanlder.mail.getToAddress(), hanlder.mail.getFromAddress(), hanlder.mail.getSubject(),
								hanlder.mail.getMessage(), hanlder.mail.getAltSmtpHost(), debug);

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
