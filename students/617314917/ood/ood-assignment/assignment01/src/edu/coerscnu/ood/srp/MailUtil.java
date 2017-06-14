package edu.coerscnu.ood.srp;

/**
 * 邮件公共类 1、配置服务器 2、发送邮件
 * 
 * @author xujie
 *
 */
public class MailUtil {

	private static String smtpHost; // 主服务器
	private static String altSmtpHost; // 备用服务器
	private static String fromAddress; // 发件人
	private static boolean isDebug; // 是否为调试环境

	/**
	 * 配置服务器
	 */
	public static void configureHost() {
		Configuration config = new Configuration();
		smtpHost = (String) config.getProperty(ConfigurationKeys.SMTP_SERVER);
		altSmtpHost = (String) config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
		fromAddress = (String) config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
		isDebug = (boolean) config.getProperty(ConfigurationKeys.IS_DEBUG);
	}

	/**
	 * 发送邮件，对外不可见
	 * 
	 * @param toAddress
	 * @param fromAddress
	 * @param subject
	 * @param message
	 * @param smtpHost
	 * @param debug
	 */
	private static void sendMail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
			boolean debug) {
		// 假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());
	}

	/**
	 * 发送邮件，对外可见
	 * 
	 * @param toAddress
	 * @param subject
	 * @param message
	 */
	public static void sendMail(String toAddress, String subject, String message) {
		configureHost();
		if (isDebug) {
			System.out.println("调试环境");
		} else {
			System.out.println("正式环境");
		}
		if (smtpHost != null) {
			System.out.println("使用主服务器发送邮件");
			sendMail(toAddress, fromAddress, subject, message, smtpHost, isDebug);
		} else if (altSmtpHost != null) {
			System.out.println("使用备用服务器发送邮件");
			sendMail(toAddress, fromAddress, subject, message, altSmtpHost, isDebug);
		} else {
			System.out.println("服务器异常，无法发送邮件");
		}

	}
}
