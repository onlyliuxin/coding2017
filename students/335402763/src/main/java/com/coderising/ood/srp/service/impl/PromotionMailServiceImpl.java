package com.coderising.ood.srp.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.coderising.ood.srp.dao.PromotionMailDao;
import com.coderising.ood.srp.dao.impl.PromotionMailDaoImpl;
import com.coderising.ood.srp.service.PromotionMailService;
import com.coderising.ood.srp.utils.FileUtil;
import com.coderising.ood.srp.utils.MailUtil;
import com.coderising.ood.srp.utils.PropertiesUtils;

public class PromotionMailServiceImpl implements PromotionMailService {

	private PromotionMailDao promotionMailDao = new PromotionMailDaoImpl();

	protected String smtpHost = (String) PropertiesUtils.get(PropertiesUtils.SMTP_SERVER);;
	protected String altSmtpHost = (String) PropertiesUtils.get(PropertiesUtils.ALT_SMTP_SERVER);;
	protected String fromAddress = (String) PropertiesUtils.get(PropertiesUtils.EMAIL_ADMIN);

	@Override
	public void sendPromotionMail(File file, boolean mailDebug) throws Exception {

		// 读取商品信息
		String[] productData = FileUtil.readFile(file);
		String productID = productData[0];
		String productDesc = productData[1];

		List mailingList = promotionMailDao.loadMailingList(productID);

		sendEMails(mailDebug, mailingList, productDesc);

	}

	
	/**
	 * 发送邮件
	 * @param debug
	 * @param mailingList
	 * @param productDesc
	 * @throws IOException
	 */
	protected void sendEMails(boolean debug, List mailingList, String productDesc) throws IOException {

		System.out.println("开始发送邮件");

		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {

				HashMap usrInfo = (HashMap) iter.next();

				HashMap eMailInfo = MailUtil.configureEMail(usrInfo, productDesc);
				String toAddress = (String) eMailInfo.get("toAddress");
				String subject = (String) eMailInfo.get("subject");
				String message = (String) eMailInfo.get("message");
				
				try {
					if (toAddress.length() > 0)
						MailUtil.sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);
				} catch (Exception e) {

					try {
						MailUtil.sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug);

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
