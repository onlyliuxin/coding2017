package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {


	protected String sendMailQuery = null;

	private static Configuration config; 
	
	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	

	public static void main(String[] args) throws Exception {

		File f = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		boolean emailDebug = false;
		PromotionMail pe = new PromotionMail(f, emailDebug);

	}

	
	public PromotionMail(File file, boolean mailDebug) throws Exception {
		
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		Product product=Product.readFile(file);
		config = new Configuration();
		Server server =new Server(); 
		server.setSmtpHost(config.getProperty(ConfigurationKeys.SMTP_SERVER));
		server.setAltSmtpHost(config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER)); 
	    DBUtil dbUtil = new DBUtil();
	    dbUtil.setLoadQuery(product.getProductID());
	    List mailingList= dbUtil.queryForList();
		if (mailingList != null) {
			System.out.println("开始发送邮件");
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				HashMap userInfo = (HashMap) iter.next();
				String toAddress = (String) userInfo.get(EMAIL_KEY);
				String name = (String) userInfo.get(NAME_KEY);
				String subject = "您关注的产品降价了";
				String message = "尊敬的 "+name+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;
				String fromAddress=config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
				EMail eMail = new EMail(toAddress,fromAddress,subject,message);
		        sendEMails(eMail,server,mailDebug); 
		}
      }else{
    	  System.out.println("没有邮件发送");
		}
	}
	
	protected void sendEMails(EMail eMail,Server server,boolean debug) throws IOException {
				try 
				{
					if (eMail.getToAddress().length() > 0)
						MailUtil.sendEmail(eMail.getToAddress(), eMail.getFromAddress(), eMail.getSubject(), eMail.getMessage(), server.getSmtpHost(), debug);
				} 
				catch (Exception e) 
				{
					
					try {
						MailUtil.sendEmail(eMail.getToAddress(), eMail.getFromAddress(), eMail.getSubject(), eMail.getMessage(), server.getAltSmtpHost(), debug); 
						
					} catch (Exception e2) 
					{
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
					}
				}
			}
}

