package com.coderising.ood.srp;

public class MailUtil {
    private static Mail firstSMPTHost, secondSMPTHost;

    static {
		String host1 = Configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
		String host2 = Configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
		firstSMPTHost = new Mail(host1);
		secondSMPTHost = new Mail(host2);
	}


	public static void sendEmail(Message msg) throws Exception{
		//假装发了一封邮件

		if (msg != null) {

			System.out.println("开始发送邮件");

			if (msg.checkFormat()) {

				System.out.println("发送邮件...");

				try {

					firstSMPTHost.send(msg);

					System.out.println("发送邮件成功");

				} catch (Exception e) {
					try {

						secondSMPTHost.send(msg);

					} catch (Exception e2) {

						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());

					}
				}
			} else {

				System.out.println("邮件格式不对");

			}
		} else {

			System.out.println("没有邮件发送");

		}
	}
}
