package com.coderising.ood.srp;

import com.coderising.ood.srp.domain.mail.MailConfigInfo;
import com.coderising.ood.srp.domain.mail.MailInfo;
import com.coderising.ood.srp.domain.product.ProductInfo;
import com.coderising.ood.srp.service.ProductInfoService;
import com.coderising.ood.srp.service.PromotionMailService;
import com.coderising.ood.srp.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by justin on 17/6/19.
 */
public class Main {

	public static void main(String[] args) {
		//查询商品信息
		ProductInfoService productInfoService = new ProductInfoService();
		ProductInfo productInfo = productInfoService.selectProduct();

		//查询用户信息
		UserService userService = new UserService();
		List<Map<String,String>> userList = userService.querySubscriptions(productInfo.getProductID());

		//构造邮件配置及发件人信息
		Configuration configuration = new Configuration();
		MailConfigInfo mailConfigInfo = new MailConfigInfo();
		mailConfigInfo.setSmtpHost(configuration.getProperty(ConfigurationKeys.SMTP_SERVER));
		mailConfigInfo.setAltSmtpHost(configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
		mailConfigInfo.setFromAddress(configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN));

		//构造邮件内容及收件人信息
		List<MailInfo> mailInfos = new ArrayList<MailInfo>(userList.size());
		for (Map<String,String> map : userList) {
			MailInfo mailInfo = new MailInfo();
			mailInfo.setMessage("尊敬的 "+map.get("NAME")+", 您关注的产品 " + productInfo.getProductDesc() + " 降价了，欢迎购买!");
			mailInfo.setSubject("您关注的产品降价了");
			mailInfo.setToAddress(map.get("EMAIL"));

			mailInfos.add(mailInfo);
		}

		PromotionMailService promotionMailService = new PromotionMailService();

		promotionMailService.sendEMails(mailConfigInfo,mailInfos,true);
	}
}
