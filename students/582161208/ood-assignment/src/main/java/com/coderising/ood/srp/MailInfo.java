package com.coderising.ood.srp;

import lombok.Data;

/**
 * 邮件基本信息
 *
 * @author ida 2017/6/12
 */
@Data
public class MailInfo {

	private String smtpHost;

	private String altSmtpHost;

	private String fromAddress;

	private String toAddress;
	
	private String subject;

	private String message;

}
