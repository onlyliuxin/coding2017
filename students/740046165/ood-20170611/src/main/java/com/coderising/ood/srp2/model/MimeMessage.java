package com.coderising.ood.srp2.model;

import java.util.Date;

/**
 * 简单邮件
 * @author mazan
 *
 */
public class MimeMessage {
	
	private String fromAddress = null;
	private String toAddress = null;
	private String subject = null;
	private String content = null;
	private Date sendDate = null;
	
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	
	
}
