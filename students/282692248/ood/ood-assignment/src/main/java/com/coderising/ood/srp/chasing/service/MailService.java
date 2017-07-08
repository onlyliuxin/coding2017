package com.coderising.ood.srp.chasing.service;

import com.coderising.ood.srp.chasing.Configuration;
import com.coderising.ood.srp.chasing.ConfigurationKeys;
import com.coderising.ood.srp.chasing.util.MailUtil;

public class MailService {
	protected String smtpHost = null;
	protected String altSmtpHost = null; 
	protected String systemAddress = null;
	
	public MailService(Configuration config){
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
		systemAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}
	
	/**
	 * 发送邮件
	 * @param toAddress	收信人
	 * @param subject	主题
	 * @param message	正文
	 */
	public void sendMail(String toAddress, String subject, String message){
		if(toAddress == null || toAddress.length() <= 0){
			return;
		}
		try{
			MailUtil.sendEmail(toAddress, systemAddress, subject, message, smtpHost);
		}catch(Exception e){
			try{
				MailUtil.sendEmail(toAddress, systemAddress, subject, message, altSmtpHost);
			}catch(Exception e2){
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
			}
		}
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
		return systemAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.systemAddress = fromAddress;
	}
}
