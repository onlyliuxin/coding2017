package com.coderising.ood.srp.good1;


public class MailSender {
	
	private String fromAddress ;
	private String smtpHost;
	private String altSmtpHost;
	
	public MailSender(Configuration config){
		this.fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
		this.smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
		this.altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
	}
	
	public void sendMail(Mail mail){
		try{
			sendEmail(mail,this.smtpHost);
		}catch(Exception e){
			try{
				sendEmail(mail,this.altSmtpHost);
			}catch (Exception ex){
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + ex.getMessage()); 
			}
			
		}
	}
	
	private void sendEmail(Mail mail, String smtpHost){
		
		String toAddress = mail.getAddress();
		String subject = mail.getSubject();
		String msg = mail.getBody();
		//发送邮件
	}
}
