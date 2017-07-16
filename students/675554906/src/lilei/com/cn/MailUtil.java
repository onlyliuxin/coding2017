package lilei.com.cn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * MailUtil 邮件方法类   
 * 个人理解，唯一引起变化的就是邮件发送方法的改变
 * 
 * */
public class MailUtil {

	private static MailAssemble assembleMail = new MailAssemble();
	
	public static void sendEmail(MailAssemble assembleMail , boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(assembleMail.fromAddress).append("\n");
		buffer.append("To:").append(assembleMail.toAddress).append("\n");
		buffer.append("Subject:").append(assembleMail.subject).append("\n");
		buffer.append("Content:").append(assembleMail.message).append("\n");
		System.out.println(buffer.toString());
		
	}
	
	protected void sendEMails(boolean debug) throws Exception 
	{	
		assembleMail.assembleMail();
		List mailingList = assembleMail.loadMailingList();
		System.out.println("开始发送邮件");
		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				assembleMail.configureEMail((HashMap) iter.next());  
				try 
				{
					if (assembleMail.toAddress.length() > 0)
						sendEmail(assembleMail, debug);
				} 
				catch (Exception e) 
				{
					
					try {
						sendEmail(assembleMail, debug); 
						
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
