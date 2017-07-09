package com.coderising.ood.srp;

public class MailUtil {


	/**
	 * 邮件单元是需要管理邮件的，包括发送到哪，端口主机等等
	 */
	private static String fromAddress = "";
	private static String smtpHost = "";
	private static String altSmtpHost = "";


	private static Configuration config = new Configuration();

	public static void ConfigureEmail() {
		altSmtpHost = (String) config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
		fromAddress = (String) config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
		smtpHost = (String) config.getProperty(ConfigurationKeys.SMTP_SERVER);

	}


	public static void sendEmail(String toAddress,String subject,String message) {
		ConfigureEmail();
		//假装发了一封邮件
		try{
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
