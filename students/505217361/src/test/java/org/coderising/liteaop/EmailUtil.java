package test.java.org.coderising.liteaop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;



public class EmailUtil {
	
	private static final String EMAIL_KEY = "EMAIL";
	protected String smtpHost = null;
	protected String altSmtpHost = null; 
	protected String fromAddress = null;
	protected String subject  = null;
	protected String message  = null;
	
	
	
	public void sendEmail(List users,Production product){
		boolean debugs = false;
		// 获取配置信息
		Configuration config = new Configuration(); 	
		smtpHost = config.setSMTPHost();
		altSmtpHost = config.setAltSMTPHost();
		fromAddress = config.setFromAddress();
		System.out.println("开始发送邮件");
		if(users != null){
			Iterator iter = users.iterator();
			while(iter.hasNext()){
				User user = (User) iter.next();
				String userEmail = user.getEmailadd();
				String userName = user.getUsername();
				
				// 获取输入值
				setMessage(userName,product);
				
				try{
					if(userEmail.length()>0){
						Mail mail = new Mail();
						mail.setFromAddress(fromAddress);
						mail.setUserEmail(userEmail);
						mail.setSmtpHost(altSmtpHost);
						mail.setSubject(subject);
						mail.setMessage(message);
						
						mail.sendEmail(debugs);
						
					}
				}catch(Exception e ){
					try{
						
							Mail mail = new Mail();
							mail.setFromAddress(fromAddress);
							mail.setUserEmail(userEmail);
							mail.setSmtpHost(altSmtpHost);
							mail.setSubject(subject);
							mail.setMessage(message);
							
							System.out.println("使用备用服务器地址发送邮件!");
							mail.sendEmail(debugs);
							
						
					}catch(Exception e2){
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
					}
				}
				
				
			}
		}else {
			System.out.println("没有邮件发送");
			
		}
		
		
	}

	

	protected void setMessage(String username,Production product) 
	{
	
		String name = username;		
		subject = "您关注的产品降价了";
		message = "尊敬的 "+name+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;		
		
	}





	
}
