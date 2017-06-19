package com.coderising.ood.srp;

import com.coderising.ood.srp.domainlogic.Email;
import com.coderising.ood.srp.domainlogic.EmailService;

public class EmailServiceImpl implements EmailService {
	private boolean debugMode = false; // 调试模式，默认关闭
	private String priorSmtpHost;
	private String altSmtpHost;

	public EmailServiceImpl(String priorSmtpHost, String altSmtpHost) {
		this.priorSmtpHost = priorSmtpHost;
		this.altSmtpHost = altSmtpHost;
	}

	@Override
	public void sendEmail(Email email) {

		try {
			sendEmail(email, priorSmtpHost);
		} catch (Exception e) {

			try {
				sendEmail(email, altSmtpHost);
			} catch (Exception e2) {
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
			}
		}
	}

	private void sendEmail(Email email,String smtpHost)
	{
		// TODO: send email
		if (isDebugMode()) {
			StringBuilder buffer = new StringBuilder();
			buffer.append("From:").append(email.getFromAddress()).append("\n");
			buffer.append("To:").append(email.getToAddress()).append("\n");
			buffer.append("Subject:").append(email.getSubject()).append("\n");
			buffer.append("Content:").append(email.getMessage()).append("\n");
			System.out.println(buffer.toString());
		}
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

}
