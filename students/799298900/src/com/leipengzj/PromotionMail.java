package com.leipengzj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {


//	protected String sendMailQuery = null;
//
//
//	protected String smtpHost = null;
//	protected String altSmtpHost = null;
//	protected String fromAddress = null;
//	protected String toAddress = null;
//	protected String subject = null;
//	protected String message = null;
//
//	protected String productID = null;
//	protected String productDesc = null;

	private static Configuration config; 
	
	
	
	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	

	public static void main(String[] args) throws Exception {

		File f = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		boolean emailDebug = false;

		PromotionMail pe = new PromotionMail(f, emailDebug);

	}

	//发送邮件
	public PromotionMail(File file, boolean mailDebug) throws Exception {
		MailInfo mi = new MailInfo();

		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		readFile(mi,file);


		config = new Configuration();

		mi.setSmtpHost(config.getProperty(ConfigurationKeys.SMTP_SERVER));
		mi.setAltSmtpHost(config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
		mi.setFromAddress(config.getProperty(ConfigurationKeys.EMAIL_ADMIN));

		String sql = "Select name from subscriptions "
				+ "where product_id= '" + mi.getProductID() +"' "
				+ "and send_mail=1 ";
		//查询出邮件列表
		List query = DBUtil.query(sql);

		sendEMails(mi,mailDebug, query);

		
	}


	//读取产品信息
	protected void readFile(MailInfo mi,File file) throws IOException // @02C
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			mi.setProductID(data[0]);
			mi.setProductDesc(data[1]);
			
			System.out.println("产品ID = " + data[0] + "\n");
			System.out.println("产品描述 = " + data[1] + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}

    //配置邮件并设置发送的邮件内容
	protected void configureEMail(HashMap userInfo,MailInfo mi) throws IOException
	{
		String toAddress = (String) userInfo.get(EMAIL_KEY);
		String name = (String) userInfo.get(NAME_KEY);
		if (toAddress.length() > 0){
		   mi.setSubject("您关注的产品降价了");
		   mi.setMessage("尊敬的 "+name+", 您关注的产品 " + mi.getProductDesc() + " 降价了，欢迎购买!");
		}

	}


	protected void sendEMails(MailInfo mi,boolean debug, List mailingList) throws IOException
	{

		System.out.println("开始发送邮件");
	

		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				configureEMail((HashMap) iter.next(),mi);
				try 
				{
					if (mi.getToAddress().length() > 0)
						MailUtil.sendEmail(mi.getToAddress(), mi.getToAddress(), mi.getSubject(), mi.getMessage(), mi.getSmtpHost(), debug);
				} 
				catch (Exception e) 
				{
					
					try {
						MailUtil.sendEmail(mi.getToAddress(), mi.getToAddress(), mi.getSubject(), mi.getMessage(), mi.getAltSmtpHost(), debug);
						
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
