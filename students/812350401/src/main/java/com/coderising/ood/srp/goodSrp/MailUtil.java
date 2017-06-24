package com.coderising.ood.srp.goodSrp;


import com.coderising.ood.srp.goodSrp.template.MailBodyTemplate;
import com.coderising.ood.srp.goodSrp.template.TextMailBodyTemplate;

public class MailUtil {

	public static void sendEmail(Mail mail, String smtpHost, String fromAddress) {
		//假装发了一封邮件
		System.out.println("使用smtpHost为"+smtpHost);
		MailBodyTemplate template = new TextMailBodyTemplate(mail, fromAddress);
		System.out.println(template.render());
	}

	
}
