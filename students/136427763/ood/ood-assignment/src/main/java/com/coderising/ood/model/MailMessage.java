
package com.coderising.ood.model; 

import java.util.HashMap;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2017年6月17日 下午9:13:21 
 * 类说明 
 */
public class MailMessage  {
	
	private String subject;
	
	private String message;
	 
	private String toAddress;

	
	public void createProductMessage(Product product,HashMap userInfo) {
		subject = "您关注的产品降价了";
		message = "尊敬的 "+userInfo.get("NAME")+", 您关注的产品 " + product.getmProductDesc() + " 降价了，欢迎购买!" ;
		toAddress=(String) userInfo.get("EMAIL");
	}

	public String getSubject() {
		return subject;
	}

	public String getToAddress() {
		return toAddress;
	}

	public String getMessage() {
		return message;
	}
}
 