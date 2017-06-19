package com.coderising.ood.srp;

public class Email {
	private String smtpHost;
	private String altSmtpHost; 
	private String fromAddress;
	private String toAddress;
	private String subject;
	private String message;
	private boolean debug;
	
	public Email() {
		
	}
	
	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getAltSmtpHost() {
		return altSmtpHost;
	}

	public void setAltSmtpHost(String altSmtpHost) {
		this.altSmtpHost = altSmtpHost;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void sendToTarget() {
		try {
			if (toAddress.length() > 0)
				MailUtil.sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);
		} catch (Exception e) {
			try {
				MailUtil.sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug); 
				
			} catch (Exception e2) {
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
			}
		}
	}
}
