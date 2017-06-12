package com.coderising.ood.srp;

import lombok.Data;

@Data
public class MailInfo {

	private String smtpHost;
	
	private String altSmtpHost;
	
	private String fromAddress;
	
	private String toAddress;
	
	private String subject;
	
	private String message;

}
