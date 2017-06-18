package Model;

import configurations.Configuration;
import configurations.ConfigurationKeys;

public class Message {
	
	private String subject;
	private String message;
	private String toAddress;
	private String fromAddress;
	
	public void setMessage(UserInfo userInfo, ProductInfo productInfo) {
		//从配置文件读取 有可能变化为主动设置
		fromAddress = Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
		//包含于用户信息中
		toAddress = userInfo.getAddress();
		subject = "您关注的产品降价了";
		message = "尊敬的 "+userInfo.getName()+", 您关注的产品 " + productInfo.getProductDesc() + " 降价了，欢迎购买!" ;	
	}
	
	public boolean hasValidAddress() {
		if (toAddress.length() > 0) {
			return true;
		}
		return false;
	}
	
	public String getSubject() {
		return subject;
	}
	public String getMessage() {
		return message;
	}
	public String getToAddress() {
		return toAddress;
	}
	public String getFromAddress() {
		return fromAddress;
	}
}
