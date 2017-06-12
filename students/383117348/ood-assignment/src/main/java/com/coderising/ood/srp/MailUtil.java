package com.coderising.ood.srp;

public class MailUtil {

	private static String fromAddress = "";
	private static String smtpHost = "";
	private static String altSmtpHost = "";
	private static boolean debug = false;

	private static Configuration config = new Configuration();

	private static void ConfigureEmail() {

		altSmtpHost = (String) config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
		fromAddress = (String) config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
		smtpHost = (String) config.getProperty(ConfigurationKeys.SMTP_SERVER);
		debug = (Boolean) config.getProperty(ConfigurationKeys.IS_EMAIL_DEBUG);
	}
	/**
	 * 发送单条邮件
	 * @param toAddress
	 * @param subject
	 * @param message
	 */
	public static void sendEmail(String toAddress,String subject,String message) {
		ConfigureEmail();
		if(debug){
			System.out.println("测试环境");
		}else{
			System.out.println("正式环境");
		}
		try {
			//假装发了一封邮件
			StringBuilder buffer = new StringBuilder();
			buffer.append("From:").append(fromAddress).append("\n");
			buffer.append("To:").append(toAddress).append("\n");
			buffer.append("Subject:").append(subject).append("\n");
			buffer.append("Content:").append(message).append("\n");
			System.out.println(buffer.toString());
		} catch (Exception e) {
			try {
				System.out.println("备用SMTP服务器: ");
				MailUtil.sendEmail(toAddress, subject, message);
				
			} catch (Exception e2) {
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
			}
		}

	}
	
}
