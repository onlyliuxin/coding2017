package test.java.org.coderising.liteaop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;



public class Mail {
	String fromAddress;
	String userEmail;
	String smtpHost;
	String subject;
	String message;
	
	
	public String getFromAddress() {
		return fromAddress;
	}


	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getSmtpHost() {
		return smtpHost;
	}


	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
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


	public  void sendEmail(boolean debug) {
		
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(userEmail).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());
		
	}
	

}
