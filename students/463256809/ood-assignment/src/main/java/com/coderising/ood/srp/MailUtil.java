package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MailUtil {


	private static String sendMailQuery = null;
	private static String smtpHost = null;
	private static String altSmtpHost = null;
	private static String fromAddress = null;
	private static String toAddress = null;
	private static String subject = null;
	private static String message = null;


	private static Configuration config = new Configuration();
	private static Product product = new Product();


	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";

	protected static void setLoadQuery() throws Exception {

		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + product.getProductID() +"' "
				+ "and send_mail=1 ";


		System.out.println("loadQuery set");
	}


	protected static void setSMTPHost()
	{
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
	}


	protected static void setAltSMTPHost()
	{
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);

	}


	protected static void setFromAddress()
	{
		fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}

	protected static void setMessage(HashMap userInfo) throws IOException
	{

		String name = (String) userInfo.get(NAME_KEY);

		subject = "您关注的产品降价了";
		message = "尊敬的 "+name+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;



	}



	protected static void configureEMail(HashMap userInfo) throws IOException
	{
		toAddress = (String) userInfo.get(EMAIL_KEY);
		if (toAddress.length() > 0)
			setMessage(userInfo);
	}

	protected static List loadMailingList() throws Exception {
		return DBUtil.query(sendMailQuery);
	}


	public static void sendEMails(boolean debug, List mailingList) throws Exception
	{

		setSMTPHost();
		setAltSMTPHost();
		setFromAddress();
		setLoadQuery();

		System.out.println("开始发送邮件");


		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				configureEMail((HashMap) iter.next());
				try
				{
					if (toAddress.length() > 0)
						MailUtil.sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);
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

		else {
			System.out.println("没有邮件发送");

		}

	}

	public static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
			boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());
		
	}

	
}
