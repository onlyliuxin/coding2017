package com.coderising.ood.srp;

import java.io.File;
import java.util.List;

public class PromotionMail {

	private static ProductFileOperation operator = new ProductFileOperation();

	private static Configuration config;

	public static void main(String[] args) throws Exception {

		File f = new File("/Users/zhenli/Desktop/Code/coding2017/" +
				"students/353354608/ood/ood-assignment/src/main/java" +
				"/com/coderising/ood/srp/product_promotion.txt");

		//读取文件,初始化产品
		String[] data = operator.readFile(f);
		Product product = new Product(data[0],data[1]);

		//读取配置
		config = new Configuration();

		//启动SMTPHost
		ConfigOperation configOperation = new ConfigOperation(config);
		configOperation.setSMTPHost();

		//		setAltSMTPHost();
		configOperation.setAltSMTPHost();

		//setFromAddress();
		configOperation.setFromAddress();

		//setLoadQuery();
		Query query = new Query();
		query.setLoadQuery(product);

		//loadMailingList()
		String querySQL = query.getSendMailQuery();
		List list = DBUtil.query(querySQL);

		//
		boolean emailDebug = false;
		EmailOperation emailOperation = new EmailOperation();
		emailOperation.sendEMails(emailDebug,list,product,configOperation);

	}

	

}
