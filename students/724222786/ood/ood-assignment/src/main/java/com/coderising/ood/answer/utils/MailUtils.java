package com.coderising.ood.answer.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coderising.ood.answer.entity.MailMessage;

/**
 * 邮件发送工具
 * @author readke
 *
 */
public class MailUtils {
	private static final Logger log = LogManager.getLogger(MailUtils.class);
	
	private static final String SMTP_SERVER = ConfigUtils.getProperty("smtp.server"); 
	private static final String ALT_SMTP_SERVER = ConfigUtils.getProperty("alt.smtp.server"); 
	
	public static void sendMail(MailMessage email){
		try{
			sendMail(email, SMTP_SERVER);
			log.info("使用主服务器发送邮件");
			log.info("发送成功");
		}catch (Exception e) {
			try{
				sendMail(email, ALT_SMTP_SERVER);
				log.info("使用备用服务器发送邮件");
			}catch (Exception e1){
				log.error("发送失败");
			}
		}
	}
	
	public static void sendMail(MailMessage email,String server) throws Exception{
		sendMail(email.getFromAddr(), email.getToAddr(),
				server, email.getSubject(), email.getContent());
	}
	
	public static void sendMail(String from,String to,String server,String subject,String content)  throws Exception{
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(from).append("\n");
		buffer.append("To:").append(to).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(content).append("\n");
		System.out.println(buffer.toString());
	}
	
	
}
