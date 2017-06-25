package com.coderising.ood.srp.service;

import java.util.List;

import com.coderising.ood.srp.model.MailInfo;

public interface MailService {

	void sendMail(MailInfo mail, String fromAddress, String smtpHost, boolean debug);
}
