package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MailUtil {




	public static void sendEmail(Mail mail,String smtpHost,HashMap userInfo,
								 Product product, boolean debug) throws IOException {
		mail.setMessage(userInfo, product);
		String email = encapsulation(mail);
		System.out.println(email.toString());
	}

	private static String encapsulation(Mail mail) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(mail.getFromAddress()).append("\n");
		buffer.append("To:").append(mail.getToAddress()).append("\n");
		buffer.append("Subject:").append(mail.getSubject()).append("\n");
		buffer.append("Content:").append(mail.getMessage()).append("\n");
		return buffer.toString();

	}

	public void configureEMail(HashMap userInfo,Mail mail) throws IOException
	{
		mail.getToAddress() = (String) userInfo.get(Mail.getEmailKey());
		if (toAddress.length() > 0)
			setMessage(userInfo.toString());
	}


	protected void sendEMails(boolean debug, List mailingList) throws IOException
	{

		System.out.println("开始发送邮件");


		if (mailingList != null) {

			send(mailingList);

		}

		else {
			System.out.println("没有邮件发送");

		}

	}

	public void send(List mailingList,Mail mail){
		Iterator iter = mailingList.iterator();
		while (iter.hasNext()) {
			Mail.configureEMail((HashMap) iter.next());
			try
			{
				if (toAddress.length() > 0)
					MailUtil.sendEmail(mail, smtpHost, debug);
			}
			catch (Exception e)
			{

				try {
					MailUtil.sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug);

				} catch (Exception e2)
				{
					System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
				}
			}
		}


	}


}
