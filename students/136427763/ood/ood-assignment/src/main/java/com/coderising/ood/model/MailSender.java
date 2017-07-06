package com.coderising.ood.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.coderising.ood.util.DBUtil;
import com.coderising.ood.util.MailUtil;
import com.sun.jndi.cosnaming.IiopUrl.Address;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年6月17日 下午9:11:59 类说明
 */
public class MailSender {
	
	public void sendEMails(boolean debug, List mailingList, Product product)throws IOException {
		MailMessage mailMessage = new MailMessage();
		System.out.println("开始发送邮件");
		HashMap hashMap;
		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				hashMap = (HashMap) iter.next();
				mailMessage.createProductMessage(product, hashMap);
				try {
					if (mailMessage.getToAddress().length() > 0)
						MailUtil.sendEmail(mailMessage.getToAddress(), MailSetting
								.getInstannce().getmFromAddress(), mailMessage
								.getSubject(), mailMessage.getMessage(), MailSetting
								.getInstannce().getmSmtpHost(), debug);
				} catch (Exception e) {
					try {
						MailUtil.sendEmail(mailMessage.getToAddress(), MailSetting
								.getInstannce().getmFromAddress(), mailMessage
								.getSubject(), mailMessage.getMessage(), MailSetting
								.getInstannce().getmSmtpHost(), debug);
					} catch (Exception e2) {
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2
								.getMessage());
					}
				}
			}
		}
		else {
			System.out.println("没有邮件发送");
		}
	}

}
