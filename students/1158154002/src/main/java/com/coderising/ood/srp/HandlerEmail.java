package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coderising.ood.srp.model.Constant;
import com.coderising.ood.srp.model.Mail;

public class HandlerEmail {
	
	protected String sendMailQuery;

	protected Configuration config = new Configuration();
	
	protected Mail mail=new Mail();

	protected FileLoaderImpl fileLoader=new FileLoaderImpl();
	
	public HandlerEmail(File file) {
		Map map=fileLoader.readFile(file);
		mail.setProductDesc((String)map.get("productDesc"));
		mail.setProductID((String)map.get("productID"));
		mail.setSmtpHost(config.getProperty(ConfigurationKeys.SMTP_SERVER));
		mail.setAltSmtpHost(config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
		mail.setFromAddress(config.getProperty(ConfigurationKeys.EMAIL_ADMIN));

	}

	protected void setLoadQuery() throws Exception {
		sendMailQuery = "Select name from subscriptions " + "where product_id= '" + mail.getProductID() + "' "
				+ "and send_mail=1 ";

		System.out.println("loadQuery set");
	}

	protected void setMessage(HashMap userInfo) throws IOException {
		String name = (String) userInfo.get(Constant.NAME_KEY);
		mail.setSubject("您关注的产品降价了");
		mail.setMessage("尊敬的 " + name + ", 您关注的产品 " + mail.getProductDesc() + " 降价了，欢迎购买!");
	}

	protected void configureEMail(HashMap userInfo) throws IOException {
		mail.setToAddress((String) userInfo.get(Constant.EMAIL_KEY));
		if (mail.getToAddress().length() > 0)
			setMessage(userInfo);
	}

	protected List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}

}
