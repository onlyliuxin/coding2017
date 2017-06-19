package lilei.com.cn;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
/**
 * MailAssemble 组装邮件类
 * 个人理解， 此类的职责是组装邮件，而邮件是根据Configuration(配置信息类)、ReadFile(读取信息类)来组成的，这两个类也是引起变化的因素
 * 
 * */
public class MailAssemble {
	protected String sendMailQuery = null;
	protected String smtpHost = null;
	protected String altSmtpHost = null; 
	protected String fromAddress = null;
	protected String toAddress = null;
	protected String subject = null;
	protected String message = null;
	protected String productID = null;
	protected String productDesc = null;

	private static Configuration config = new Configuration(); 
	private static ReadFile rf = new ReadFile();;
	
	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	
	//组装信息
	public void assembleMail() throws Exception{
		String data[] = rf.readFile();
		setProductID(data[0]); 
		setProductDesc(data[1]); 
		setSMTPHost();
		setAltSMTPHost(); 
		setFromAddress();
		setLoadQuery();
	}
	
	protected List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}
	
	protected void configureEMail(HashMap userInfo) throws IOException 
	{
		toAddress = (String) userInfo.get(EMAIL_KEY); 
		if (toAddress.length() > 0) 
			setMessage(userInfo); 
	}
	
	protected void setFromAddress() 
	{
			fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN); 
	}

	protected void setMessage(HashMap userInfo) throws IOException 
	{
		String name = (String) userInfo.get(NAME_KEY);
		subject = "您关注的产品降价了";
		message = "尊敬的 "+name+", 您关注的产品 " + productDesc + " 降价了，欢迎购买!" ;		
	}
	
	
	protected void setProductID(String productID) 
	{ 
		this.productID = productID; 
		
	} 

	protected String getproductID() 
	{
		return productID; 
	} 

	protected void setLoadQuery() throws Exception {
		
		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productID +"' "
				+ "and send_mail=1 ";
		
		
		System.out.println("loadQuery set");
	}

	
	protected void setSMTPHost() 
	{
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER); 
	}

	
	protected void setAltSMTPHost() 
	{
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER); 

	}
	
	private void setProductDesc(String desc) {
		this.productDesc = desc;		
	}
}
