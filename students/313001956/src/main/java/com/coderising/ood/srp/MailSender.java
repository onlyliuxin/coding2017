package com.coderising.ood.srp;

public class MailSender {

	Configuration config;

	public MailSender(Configuration config) {
		this.config = config;
	}

	public void sendEmails(Mail mail) {
		System.out.println("??????????");
		try {
			sendEmail(mail, config.getProperty(Configuration.SMTP_SERVER));
		} catch (Exception e) {
			try {
				sendEmail(mail, config.getProperty(Configuration.ALT_SMTP_SERVER));

			} catch (Exception e2) {
				System.out.println("??????? SMTP????????????????: " + e2.getMessage());
			}
		}
	}

	public void sendEmail(Mail mail, String smtpHost) {

		// ?????????????
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(config.getProperty(Configuration.EMAIL_ADMIN)).append("\n");
		buffer.append("To:").append(mail.getUser().getEmail()).append("\n");
		buffer.append("Subject:").append(mail.getSubject()).append("\n");
		buffer.append("Content:").append(mail.getMessage()).append("\n");
		System.out.println(buffer.toString());
	}

}
