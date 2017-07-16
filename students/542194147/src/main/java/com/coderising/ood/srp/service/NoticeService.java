package com.coderising.ood.srp.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.coderising.ood.srp.config.Configuration;
import com.coderising.ood.srp.config.ConfigurationKeys;
import com.coderising.ood.srp.domain.Email;
import com.coderising.ood.srp.domain.Product;
import com.coderising.ood.srp.util.DBUtil;
import com.coderising.ood.srp.util.FileUtil;
import com.coderising.ood.srp.util.MailUtil;

/**
 * 消息发布接口
 * @author 小摩托
 *
 */
public class NoticeService {

	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	private static Configuration config = new Configuration();; 
	
	public void sendEMails(File file, boolean mailDebug) throws IOException 
	{

		String[] data=FileUtil.readFile(file);
		Product product=new Product();
		product.setProductID(data[0]);
		product.setProductDesc(data[1]);
		String sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + product.getProductID() +"' "
				+ "and send_mail=1 ";
		List list=DBUtil.query(sendMailQuery);
		System.out.println("开始发送邮件");
		Email email=new Email();
		if (list != null) {
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Map userInfo=(HashMap) iter.next();
				String toAddress = (String)userInfo.get(EMAIL_KEY); 
				email.setToAddress(toAddress);
				email.setFromAddress(config.getProperty(ConfigurationKeys.EMAIL_ADMIN));
				email.setSmtpHost(config.getProperty(ConfigurationKeys.SMTP_SERVER));
				email.setAltSmtpHost(config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
				if (toAddress.length() > 0){
					String name = (String)userInfo.get(NAME_KEY);
					email.setSubject("您关注的产品降价了");
					email.setMessage("尊敬的 "+name+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!");	
				}
				try 
				{
					if (toAddress.length() > 0)
						MailUtil.sendEmail(email);
				} 
				catch (Exception e) 
				{
					try {
						MailUtil.sendEmail(email); 
						
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
	public static void main(String[] args) throws Exception {

		File f = new File("C:\\Users\\john\\Documents\\GitHub\\coding2017-2ndSeason\\students\\542194147\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
		boolean emailDebug = false;
		NoticeService ns = new NoticeService();
		ns.sendEMails(f, emailDebug);

	}
}
