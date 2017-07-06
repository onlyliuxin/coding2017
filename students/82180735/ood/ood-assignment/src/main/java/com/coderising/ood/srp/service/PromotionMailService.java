package com.coderising.ood.srp.service;

import com.coderising.ood.srp.util.MailUtil;
import com.coderising.ood.srp.domain.mail.MailConfigInfo;
import com.coderising.ood.srp.domain.mail.MailInfo;

import java.util.Iterator;
import java.util.List;

/**
 * Created by justin on 17/6/12.
 */
public class PromotionMailService {

	public void sendEMails(MailConfigInfo mailConfigInfo, List<MailInfo> mailInfos, boolean debug)
	{

		System.out.println("开始发送邮件");


		if (mailInfos != null) {
			Iterator iter = mailInfos.iterator();
			while (iter.hasNext()) {
				MailInfo mailInfo = (MailInfo) iter.next();

				try
				{
					if (mailInfo.getToAddress().length() > 0)
						MailUtil.sendEmail(mailInfo.getToAddress(), mailConfigInfo.getFromAddress(), mailInfo.getSubject(), mailInfo.getMessage(),
								mailConfigInfo.getSmtpHost(), debug);
				}
				catch (Exception e)
				{
					try {
						MailUtil.sendEmail(mailInfo.getToAddress(), mailConfigInfo.getFromAddress(), mailInfo.getSubject(), mailInfo.getMessage(),
								mailConfigInfo.getAltSmtpHost(), debug);

					} catch (Exception e2)
					{
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
