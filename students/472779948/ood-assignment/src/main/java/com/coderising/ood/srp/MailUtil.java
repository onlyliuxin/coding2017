package com.coderising.ood.srp;

import java.util.List;

public class MailUtil {

	public static void sendEmail(Mail mail, boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(mail.getFromAddress()).append("\n");
		buffer.append("To:").append(mail.getToAddress()).append("\n");
		buffer.append("Subject:").append(mail.getSubject()).append("\n");
		buffer.append("Content:").append(mail.getMessage()).append("\n");
		System.out.println(buffer.toString());

	}

	public static List loadMailingList(String productID) throws Exception {
		return DBUtil.query(productID);
	}

	
}
