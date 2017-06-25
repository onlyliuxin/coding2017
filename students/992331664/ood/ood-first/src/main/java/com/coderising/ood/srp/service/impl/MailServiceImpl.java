package com.coderising.ood.srp.service.impl;

import com.coderising.ood.srp.model.MailInfo;
import com.coderising.ood.srp.service.MailService;
import com.coderising.ood.srp.util.MailUtil;

public class MailServiceImpl implements MailService {

	@Override
	public void sendMail(MailInfo mail, String fromAddress, String smtpHost, boolean debug) {
		if (mail.getToAddress().length() > 0) {
			MailUtil.sendEmail(mail.getToAddress(), fromAddress, mail.getSubject(), mail.getMessage(), smtpHost, debug);
		}
	}

}
