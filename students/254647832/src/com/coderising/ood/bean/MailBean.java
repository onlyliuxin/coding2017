package com.coderising.ood.bean;

/**
 * <p>Title: MailBean</p>
 * <p>Description: 邮件信息</p>
 * <p>Company: smartisan</p>
 * @author Administrator
 * @date 2017年6月18日
 */
public class MailBean {
	
	/**
	 * 收件箱地址
	 */
	private String toAddress;
	
	/**
	 * 邮件主题
	 */
	private String subject;
	
	/**
	 * 邮件内容
	 */
	private String message;

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

	@Override
	public String toString() {
		return "MailBean [toAddress=" + toAddress + ", subject=" + subject
				+ ", message=" + message + "]";
	}

}
