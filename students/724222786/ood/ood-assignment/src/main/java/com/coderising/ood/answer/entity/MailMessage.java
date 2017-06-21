package com.coderising.ood.answer.entity;

import java.io.Serializable;

import com.coderising.ood.answer.utils.ConfigUtils;

/**
 * 邮件类
 * @author readke
 *
 */
public class MailMessage implements Serializable{
	
	private static final long serialVersionUID = -3221160075625357827L;
	
	private String toAddr;
	private String fromAddr;
	private String subject;
	private String content;
	
	public static MailMessage getMessage(String from,String subject,String content,Product p,User u){
		MailMessage m = new MailMessage();
		if(content != null && !content.isEmpty())
		content = "尊敬的 "+u.getName()+", 您关注的产品 " + p.getpDec() + " 降价了，欢迎购买!" ;
		if(subject != null && !subject.isEmpty()){
			subject = "您关注的产品降价了";
		}
		if(from != null && !from.isEmpty()){
			from = ConfigUtils.getProperty("smtp.server");
		}
		m.setFromAddr(from);
		m.setToAddr(u.getEmail());
		m.setSubject(subject);
		m.setContent(content);
		return m;
	}
	
	public String getToAddr() {
		return toAddr;
	}
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}
	public String getFromAddr() {
		return fromAddr;
	}
	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
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
	
}
