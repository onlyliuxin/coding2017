package main.java.com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;

public class ToAddressAndMessage {

	private String productDesc = new ProductInfo().getProductDesc();

	protected String toAddress = null;
	private	String fromAddress = new FromAddress().getFromAddress();

	private String subject = null;
	private String message = null;

	private static final String EMAIL_KEY = "EMAIL";
	private static final String NAME_KEY = "NAME";

	/**
	 * 
	 * @param userInfo
	 * @throws IOException
	 */
	protected void addressAndMessage(HashMap<?, ?> userInfo) throws IOException {
		configureEMail(userInfo);
		setMessage(userInfo);
	}

	protected void configureEMail(HashMap<?, ?> userInfo) throws IOException {

		toAddress = (String) userInfo.get(EMAIL_KEY);
		if (toAddress.length() > 0)
			setMessage(userInfo);
	}

	protected void setMessage(HashMap<?, ?> userInfo) throws IOException {

		String name = (String) userInfo.get(NAME_KEY);

		subject = "您关注的产品降价了";
		message = "尊敬的 " + name + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";
	}

	protected void sendSMTPHostWay(String smtpHost, boolean debug) {
		MailUtil.sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);
	}

	protected void sendAltSMTPHostWay(String altSmtpHost, boolean debug) {
		MailUtil.sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug);
	}

}
