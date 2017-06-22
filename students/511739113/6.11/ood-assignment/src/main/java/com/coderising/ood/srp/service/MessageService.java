package com.coderising.ood.srp.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.coderising.ood.srp.bean.Message;
import com.coderising.ood.srp.bean.Product;
import com.coderising.ood.srp.config.Configuration;
import com.coderising.ood.srp.config.ConfigurationKeys;
import com.coderising.ood.srp.util.MailUtil;

/**
 * 推送消息 
 * <p>标题: </p>
 * <p>描述: </p>
 * @autho zx
 * @time 2017年6月13日 上午1:10:27
*/
public class MessageService {
	
	private static final String EMAIL_KEY = "EMAIL";
	
	private static final String NAME_KEY = "NAME";
	
	private Configuration configuration = new Configuration();
	
	/**
	 * 推送消息
	 * <p>方法名称：</p>
	 * <p>方法说明：</p>
	 * @param debug
	 * @param mailingList
	 * @param product
	 * @throws IOException
	 * @autho zx
	 * @time 2017年6月13日 上午1:59:31
	 */
	public void sendEMails(boolean debug, List mailingList,Product product) throws IOException{
		System.out.println("开始发送邮件");
		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				Message message = configureEMail((HashMap) iter.next(),product.getProductDesc());  
				try {
					if (message!=null)
						MailUtil.sendEmail(message, debug);
				}catch (Exception e){
					try {
						MailUtil.sendEmail(message, debug); 
					} catch (Exception e2){
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
					}
				}
			}
		}else {
			System.out.println("没有邮件发送");
		}
	}
	
	private Message configureEMail(HashMap userInfo,String productDesc) throws IOException{
		String toAddress = (String) userInfo.get(EMAIL_KEY); 
		if (toAddress.length() > 0) 
			return getMessage(userInfo,productDesc,toAddress); 
		return null;
	}
	
	private Message getMessage(HashMap userInfo,String productDesc,String toAddress) throws IOException{
		String name = (String) userInfo.get(NAME_KEY);
		Message messageBean = new Message();
		String subject = "您关注的产品降价了";
		String message = "尊敬的 "+name+", 您关注的产品 " + productDesc + " 降价了，欢迎购买!" ;		
		messageBean.setMessage(message);
		messageBean.setSubject(subject);
		messageBean.setToAddress(toAddress);
		messageBean.setAltSmtpHost(configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
		messageBean.setSmtpHost(configuration.getProperty(ConfigurationKeys.SMTP_SERVER));
		messageBean.setFromAddress(configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN));
		return messageBean;
	}
	
}
