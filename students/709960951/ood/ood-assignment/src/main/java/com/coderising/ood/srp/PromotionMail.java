package com.coderising.ood.srp;

import java.util.Properties;

import com.coderising.ood.srp.domainlogic.PromotionEmailService;

public class PromotionMail {

	private static final String EMAIL_CONFIG = "com/coderising/ood/srp/emailconfig.properties";

	public static void main(String[] args) throws Exception {
		// 读取email配置文件
		Properties emailConfig = new Properties();
		emailConfig.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(EMAIL_CONFIG));
		// 获取配置项
		String priorSmtpHost = ConfigurationKeys.SMTP_SERVER;
		String altSmtpHost = ConfigurationKeys.ALT_SMTP_SERVER;
		String fromEmailAddress = ConfigurationKeys.SMTP_SERVER;
		// 邮件服务的实现
		EmailServiceImpl emailService = new EmailServiceImpl(priorSmtpHost, altSmtpHost);
		emailService.setDebugMode(true);
		// 从配置文件中获取产品信息
		ConfigProductRepository configProductRepository = new ConfigProductRepository();
		// 模拟生成邮件的目标用户
		MockUserRepository mockUserRepository = new MockUserRepository();

		// 促销邮件发送服务
		PromotionEmailService pes = new PromotionEmailService(emailService, configProductRepository,
				mockUserRepository);
		pes.setFromAddress(fromEmailAddress);
		System.out.println("开始发送邮件");
		pes.sendEmail();
	}
}
