package com.coderising.ood.srp;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PromotionMail {
	protected String fromAddress = null;
	protected List toAddressList = null;
	protected String subject = "您关注的产品降价了";
	protected String message = null;
	private Product product;
	private int toAddressListIndex = -1 ;
	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	public static void main(String[] args) throws Exception {
		Product product = FileUtil.readFile();
		if (product != null) {
			boolean emailDebug = false;
			Configuration config = new Configuration();
			MailUtil mailUtil = new MailUtil(config.getProperty(ConfigurationKeys.SMTP_SERVER),
					config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
			PromotionMail mail = new PromotionMail(config.getProperty(ConfigurationKeys.EMAIL_ADMIN),product);
			mailUtil.sendPromotionMail(emailDebug,mail);
		}
	}
	public PromotionMail(String fromAddress,Product product) throws Exception {
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		this.fromAddress = fromAddress;
		this.product = product;
		this.toAddressList = DBUtil.query(this.product.generateLoadQuery());
	}
	public boolean hasNextToAddress(){
		return this.toAddressListIndex < this.toAddressList.size() - 1;
	}
	public String getNextToAddress(){
		HashMap map = (HashMap)this.toAddressList.get(++this.toAddressListIndex);
		return (String)map.get(EMAIL_KEY);
	}
	protected String generateMessageToCurrentToAddress() throws IOException {
		HashMap map = (HashMap)this.toAddressList.get(this.toAddressListIndex);
		String name = (String) map.get(NAME_KEY);
		return  "尊敬的 "+name+", 您关注的产品 " + this.product.productDesc + " 降价了，欢迎购买!" ;
	}
}
