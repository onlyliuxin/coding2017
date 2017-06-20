package main.java.com.coderising.ood.srp;

import java.util.HashMap;
import java.util.Iterator;

public class SendEMails {

	private ToAddressAndMessage message = new ToAddressAndMessage();
	
	private String smtpHost = new SetProtocol().getSMTPHost();
	private String altSmtpHost = new SetProtocol().getAltSMTPHost();
	
	private LoadInformation load = new LoadInformation();

	/**
	 * 
	 * @param debug
	 * @param mailingList
	 * @throws Exception
	 */
	protected void sendEMails(boolean debug) throws Exception {

		System.out.println("开始发送邮件");

		if (load.loadMailingList() != null) {
			Iterator<?> iter = load.loadMailingList().iterator();
			while (iter.hasNext()) {
				message.configureEMail((HashMap<?, ?>) iter.next());
				try {
					if (message.toAddress.length() > 0) {
						message.sendSMTPHostWay(smtpHost, debug);
					}
				} catch (Exception e) {
					try {
						message.sendAltSMTPHostWay(altSmtpHost, debug);
					} catch (Exception e2) {
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
					}
				}
			}

		}

		else {
			System.out.println("没有邮件发送");

		}

	}
	
}
